package chess.util;

import java.util.function.Supplier;

public class retryHelper {

    private retryHelper() {
    }

    public static <T> T retryUntilNoError(Supplier<T> supplier) {
        T result;
        do {
            result = readOnce(supplier);
        } while (result == null);
        return result;
    }

    private static <T> T readOnce(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
