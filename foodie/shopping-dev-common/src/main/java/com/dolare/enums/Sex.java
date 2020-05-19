package com.dolare.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Sex {

    woman(0, "femal"),
    man(1, "male"),
    secret(2, "secret");

    public final Integer type;

    public final String value;

}
