package com.dolare.service.implement;

import com.dolare.pojo.Users;
import com.dolare.pojo.bo.UserBo;
import com.dolare.mapper.UsersMapper;
import com.dolare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UsersMapper usersMapper;

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
        user.setUsername(userBo.getUsername());
        user.setPassword(userBo.setPassword());
    }
}
