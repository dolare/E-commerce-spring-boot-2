package com.dolare.controller;

import com.dolare.service.UserService;
import com.dolare.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.util.StringUtil;

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
}
