package com.dolare.controller;

import com.dolare.pojo.bo.UserBo;
import com.dolare.service.UserService;
import com.dolare.utils.JSONResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.util.StringUtil;

@Api(value="users", tags = {"user related"})
@RestController
@RequestMapping("passport")
public class PassportController {

    @Autowired
    private UserService userService;

    @GetMapping("/usernameIsExist")
    public JSONResult usernameIsExist(@RequestParam String username) {
        // check input value
        if (StringUtil.isEmpty(username)) {
            return JSONResult.errorMsg("username can not be empty!");
        }

        // check existence
        boolean isExist = userService.queryUsernameIsExits(username);
        return isExist ? JSONResult.ok() : JSONResult.errorMsg("username doens't exist");
    }

    @PostMapping("/register")
    public JSONResult register(@RequestBody UserBo userBo) {
        String username = userBo.getUsername();

        String password = userBo.getPassword();

        String confirmPassword = userBo.getConfirmPassword();

        // if input is valid
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password) || StringUtil.isEmpty(confirmPassword)) {
            return JSONResult.errorMsg("password or username can not be empty!");
        }

        // check if user exist
        if (userService.queryUsernameIsExits(username)) {
            return JSONResult.errorMsg("username is already used by another user!");
        }
        // check password length
        if (password.length() < 6) {
            return JSONResult.errorMsg("length of password should be greater than 6!");
        }

        // check comfirm = pw
        if (!password.equals(confirmPassword)) {
            return JSONResult.errorMsg("password should be equals to the confirm password!");
        }
        // complete
        userService.createUser(userBo);

        return JSONResult.ok();
    }
}
