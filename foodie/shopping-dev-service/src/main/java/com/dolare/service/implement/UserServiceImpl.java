package com.dolare.service.implement;

import com.dolare.enums.Sex;
import com.dolare.pojo.Users;
import com.dolare.pojo.bo.UserBo;
import com.dolare.mapper.UsersMapper;
import com.dolare.service.UserService;
import com.dolare.utils.DateUtil;
import com.dolare.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.n3r.idworker.Sid;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UsersMapper usersMapper;

    @Autowired
    private Sid sid;
    public static final String USER_FACE = "https://freeiconshop.com/wp-content/uploads/edd/person-flat.png";

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean queryUsernameIsExits(String username) {
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();

        userCriteria.andEqualTo("username", username);
        Users user = usersMapper.selectOneByExample(userExample);

        return user == null ? false : true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(UserBo userBo) {
        Users user = new Users();
        String userId = sid.nextShort();
        user.setId(userId);

        user.setUsername(userBo.getUsername());
        try {
            user.setPassword(MD5.getMD5Str(userBo.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        user.setNickname(userBo.getUsername());
        user.setFace(USER_FACE);

        user.setBirthday(DateUtil.stringToDate("1990-01-01"));
        user.setSex(Sex.secret.type);
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());

        usersMapper.insert(user); // user.save

        return user;

    }
}
