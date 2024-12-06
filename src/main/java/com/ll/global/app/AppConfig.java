package com.ll.global.app;

import lombok.Getter;

public class AppConfig {
    private static String mode;
    @Getter
    private static String repositoryMode = "db";

    static {
        setDevMode();
    }

    public static void setDevMode() {
        AppConfig.mode = "dev";
    }

    public static void setProdMode() {
        AppConfig.mode = "prod";
    }

    public static void setTestMode() {
        AppConfig.mode = "test";
    }

    public static boolean isDevMode() {
        return "dev".equals(AppConfig.mode);
    }

    public static boolean isProdMode() {
        return "prod".equals(AppConfig.mode);
    }

    public static boolean isTestMode() {
        return "test".equals(AppConfig.mode);
    }

    public static String getDbDirPath() {
        return "db/" + mode;
    }

    public static String getDbName() {
        return "wiseSaying__" + mode;
    }
}
