package com.dolare.pojo.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class NewItemsVO {
    private Integer rootCatId;

    private String rootCatName;

    private String catImage;

    private String slogan;

    private String bgColor;

    private List<SimpleItemVO> simpleItemList;

}
