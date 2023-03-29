package com.jetbrains.utils;

import java.util.Base64;

public class DataloreUtils {
    public static String decodePassword(String pass) {
        var decodedBytes = Base64.getDecoder().decode(pass);
        return new String(decodedBytes);
    }
}
