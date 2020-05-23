package com.dolare.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter @Getter @AllArgsConstructor
public class ItemCommentVO {
    private Integer commentLevel;

    private String content;

    private String sepcName;

    private Date createdTime;

    private String userFace;

    private String nickname;
}
