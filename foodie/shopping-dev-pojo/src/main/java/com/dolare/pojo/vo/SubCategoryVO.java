package com.dolare.pojo.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class SubCategoryVO {
    private Integer subId;

    private String subName;

    private String subType;

    private Integer subFatherId;
}
