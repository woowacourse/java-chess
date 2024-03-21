package chess.util;

import java.util.Optional;
import java.util.function.Supplier;

public class ExceptionRetryHandler {
    private ExceptionRetryHandler() {
    }

    public static <T> T handle(Supplier<T> supplier) {
        Optional<T> result = Optional.empty();
        while (result.isEmpty()) {
            result = tryGet(supplier);
        }

        return result.get();
    }

    private static <T> Optional<T> tryGet(Supplier<T> supplier) {
        try {
            return Optional.of(supplier.get());
        } catch (IllegalArgumentException exception) {
            printExceptionMessage(exception);
            return Optional.empty();
        }
    }

    public static void handle(Runnable runnable) {
        boolean isSuccess = false;
        while (!isSuccess) {
            isSuccess = tryRun(runnable);
        }
    }

    private static boolean tryRun(Runnable runnable) {
        try {
            runnable.run();
            return true;
        } catch (IllegalArgumentException exception) {
            printExceptionMessage(exception);
            return false;
        }
    }

    private static void printExceptionMessage(IllegalArgumentException exception) {
        System.out.println(System.lineSeparator() + exception.getMessage());
        System.out.println("다시 입력해 주세요.");
    }
}
