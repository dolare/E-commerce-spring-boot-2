package com.dolare.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;

public class MD5 {

    public static String getMD5Str(String stringValue) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String newStr = Base64.encodeBase64String(md5.digest(stringValue.getBytes()));
        return newStr;
    }
}
