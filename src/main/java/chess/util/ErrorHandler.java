package chess.util;

import java.util.function.Supplier;

public class ErrorHandler {

    public static <T> T retryUntilSuccessWithReturn(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void retryUntilSuccess(Runnable runnable) {
        while (true) {
            try {
                runnable.run();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
