package com.dolare.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum YesOrNo {

    YES(1, "yes"),
    NO(0, "no");

    public final Integer type;

    public final String value;

}
