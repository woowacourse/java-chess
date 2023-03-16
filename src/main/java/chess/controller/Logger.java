package chess.controller;

public class Logger {

    private static final String ERROR_PREFIX = "[ERROR] ";

    public static void error(final Exception e) {
        System.out.println(ERROR_PREFIX + e.getMessage());
    }
}
