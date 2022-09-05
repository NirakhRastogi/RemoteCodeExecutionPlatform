package com.rce.remotecodeexecution.util;

import com.rce.remotecodeexecution.models.OperatingSystem;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OSUtil {

    private static final String OS = System.getProperty("os.name").toLowerCase();

    public static OperatingSystem getCurrentOperationSystem(){

        log.info("Service is running on operating system, {}", OS);

        if (isWindows()) {
            return OperatingSystem.WIN;
        } else if (isMac()) {
            return OperatingSystem.MAC;
        } else if (isUnix()) {
            return OperatingSystem.LINUX;
        }else {
            throw new RuntimeException("Unsupported OS, " + OS);
        }

    }

    private static boolean isWindows() {
        return (OS.contains("win"));
    }

    private static boolean isMac() {
        return (OS.contains("mac"));
    }

    private static boolean isUnix() {
        return (OS.contains("nix")
                || OS.contains("nux")
                || OS.indexOf("aix") > 0);
    }

}
