package ru.garik;

public class Config {

    //TODO: Убрать логин:пароль в аргументы
    private static final String EMAIL = "test513test@yandex.ru";
    private static final String PASSWORD = "EQgzMWy2U2VAwGQ";

    private static final int TIMEOUT_SECOND = 7;

    public static String getEMAIL() {
        return EMAIL;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static int getTimeoutSecond() {
        return TIMEOUT_SECOND;
    }

}