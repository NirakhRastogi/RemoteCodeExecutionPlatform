package com.rce.remotecodeexecution.util;

import com.rce.remotecodeexecution.constants.Constants;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.List;

public class FileUtil {

    public static void validateIfFileExists(String filePath) {
        File f = new File(filePath);
        if (!f.exists()) {
            throw new RuntimeException("File does not exists at path, " + filePath);
        }
    }

    public static void copyFile(String filePath, String outputPath, String outputFileName) {

        validateIfFileExists(filePath);
        createDirectoryIfNotExist(outputPath);
        try {
            Files.copy(Path.of(filePath), Path.of(outputPath+ Constants.FILE_PATH_SEPERATOR+outputFileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Unable to copy file from " + filePath + " to " + outputPath);
        }
    }

    private static void createDirectoryIfNotExist(String outputPath) {
        try {
            if (!Files.exists(Path.of(outputPath))) {
                Files.createDirectories(Path.of(outputPath));
            }
        }
        catch(IOException e){
            throw new RuntimeException("Unable to create directory: " + outputPath);
        }
    }

    public static String readFile(String filePath) {
        validateIfFileExists(filePath);
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath), Charset.defaultCharset());
            return String.join("\n", lines);
        } catch (IOException e) {
            throw new RuntimeException("Unable to read file " + filePath);
        }
    }

    public static void saveToFile(String code, String filePath, String fileName) {
        Path path = Path.of(filePath + "/" + fileName);
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException("Unable to create file, " + filePath + "/" + fileName);
            }
        }
        try {
            OpenOption[] options = new OpenOption[]{StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING};
            Files.writeString(path, code, Charset.defaultCharset(), options);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write data to file, " + filePath + "/" + fileName);
        }
    }
}
