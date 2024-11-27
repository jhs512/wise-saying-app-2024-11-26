package com.ll.standard.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Util {
    public static class file {
        public static void touch(String filePath) {
            set(filePath, "");
        }

        public static boolean exists(String filePath) {
            Path path = Paths.get(filePath);
            return Files.exists(path);
        }

        public static void set(String filePath, String content) {
            Path path = Paths.get(filePath);

            try {
                Files.writeString(path, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public static String get(String filePath, String defaultValue) {
            Path path = Paths.get(filePath);

            try {
                return Files.readString(path);
            } catch (IOException e) {
                return defaultValue;
            }
        }
    }
}
