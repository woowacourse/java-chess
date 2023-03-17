package chess.controller;

import java.util.function.Supplier;

public class IllegalArgumentExceptionHandler {

    private static final int LOOP_MAX = 10000;

    public static <T> T repeat(Supplier<T> supplier) {
        return repeat(supplier, 0);
    }

    public static void repeat(Runnable runnable) {
        repeat(runnable, 0);
    }

    private static <T> T repeat(Supplier<T> supplier, int loopCount) {
        if (loopCount > LOOP_MAX) {
            throw new IllegalStateException("재입력 가능 횟수를 초과했습니다");
        }
        try {
            return supplier.get();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return repeat(supplier, loopCount + 1);
        }
    }

    private static void repeat(Runnable runnable, int loopCount) {
        if (loopCount > LOOP_MAX) {
            throw new IllegalStateException("재입력 가능 횟수를 초과했습니다");
        }
        try {
            runnable.run();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            repeat(runnable, loopCount + 1);
        }
    }
}
