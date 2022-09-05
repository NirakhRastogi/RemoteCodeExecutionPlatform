package com.rce.remotecodeexecution.util;

public class CodeUtil {

    public static String modifyCode(String encodedCode, String templatePath) {

        StringBuilder decodedCode = new StringBuilder();

        String templateData = FileUtil.readFile(templatePath);

        decodedCode.append(templateData)
                .append("\n")
                .append(DataUtil.decodeBase64(encodedCode))
                .append("\n}");

        return decodedCode.toString();

    }

}
