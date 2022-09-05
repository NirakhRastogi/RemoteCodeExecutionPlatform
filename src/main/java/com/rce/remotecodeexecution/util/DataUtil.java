package com.rce.remotecodeexecution.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class DataUtil {

    public static String decodeBase64(String input) {
        return new String(Base64.getDecoder().decode(input), StandardCharsets.UTF_8);
    }

    public static String encodeBase64(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

}
