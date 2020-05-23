package com.dolare.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CommentLevel {

    GOOD(1, "good"),
    NORMAL(2, "normal"),
    BAD(3, "bad");

    public final Integer type;

    public final String value;

}
