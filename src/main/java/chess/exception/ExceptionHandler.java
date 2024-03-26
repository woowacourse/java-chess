package chess.exception;

import java.util.function.Supplier;

public class ExceptionHandler {

    private ExceptionHandler() {
    }

    public static <T> T retry(final Supplier<T> callback) {
        try {
            return callback.get();
        } catch (IllegalArgumentException exception) {
            printExceptionMessage(exception);
            return retry(callback);
        }
    }

    public static void retry(final Runnable callback) {
        try {
            callback.run();
        } catch (IllegalArgumentException exception) {
            printExceptionMessage(exception);
            retry(callback);
        }
    }

    private static void printExceptionMessage(final IllegalArgumentException exception) {
        System.out.printf("[ERROR] " + exception.getMessage() + "%n%n다시 입력하세요.%n");
    }
}
