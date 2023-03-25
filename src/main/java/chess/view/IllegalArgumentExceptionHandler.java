package chess.view;

import java.util.function.Supplier;

public class IllegalArgumentExceptionHandler {

    private static final int LOOP_MAX = 10000;

    public static <T> T handleExceptionByRepeating(Supplier<T> supplier) {
        return handleExceptionByRepeating(supplier, 0);
    }

    public static void handleExceptionByRepeating(Runnable runnable) {
        handleExceptionByRepeating(runnable, 0);
    }

    private static <T> T handleExceptionByRepeating(Supplier<T> supplier, int loopCount) {
        if (loopCount > LOOP_MAX) {
            throw new IllegalStateException("재입력 가능 횟수를 초과했습니다");
        }
        try {
            return supplier.get();
        } catch (IllegalArgumentException exception) {
            System.out.println("[ERROR] " + exception.getMessage());
            return handleExceptionByRepeating(supplier, loopCount + 1);
        }
    }

    private static void handleExceptionByRepeating(Runnable runnable, int loopCount) {
        if (loopCount > LOOP_MAX) {
            throw new IllegalStateException("재입력 가능 횟수를 초과했습니다");
        }
        try {
            runnable.run();
        } catch (IllegalArgumentException exception) {
            System.out.println("[ERROR] " + exception.getMessage());
            handleExceptionByRepeating(runnable, loopCount + 1);
        }
    }
}
