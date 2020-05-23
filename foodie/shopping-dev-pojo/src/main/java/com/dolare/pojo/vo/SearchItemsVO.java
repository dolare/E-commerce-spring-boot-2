package com.dolare.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class SearchItemsVO {
    private String itemId;

    private String itemName;

    private int sellCounts;

    private String imgUrl;

    private int price;
}
