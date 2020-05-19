package com.dolare.pojo.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserBo {

    private String username;

    private String password;

    @ApiModelProperty(required = false)
    private String confirmPassword;
}
