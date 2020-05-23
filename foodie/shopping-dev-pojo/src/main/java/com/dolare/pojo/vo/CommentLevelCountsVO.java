package com.dolare.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor
public class CommentLevelCountsVO {

    private Integer totalCounts;

    private Integer goodCounts;

    private Integer normalCounts;

    private Integer badCounts;
}
