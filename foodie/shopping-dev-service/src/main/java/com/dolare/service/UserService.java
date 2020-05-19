package com.dolare.service;

import com.dolare.pojo.Users;
import com.dolare.pojo.bo.UserBo;
public interface UserService {
    public boolean queryUsernameIsExits (String username);

    public Users createUser(UserBo userBO);

    public Users queryUserForLogin(String username, String password);
}
