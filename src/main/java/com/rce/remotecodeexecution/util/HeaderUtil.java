package com.rce.remotecodeexecution.util;

import org.springframework.util.MultiValueMap;

import java.util.Objects;

public class HeaderUtil {

    public static String getHeaderValue(MultiValueMap<String, String> headers, String key) {

        if (headers.containsKey(key) && Objects.nonNull(headers.get(key))) {
            return headers.get(key).get(0);
        }

        throw new RuntimeException("Unable to find header key: " + key);

    }

}
