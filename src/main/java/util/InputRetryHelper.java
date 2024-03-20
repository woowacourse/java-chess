package util;

import java.util.function.Supplier;

public class InputRetryHelper {
    private InputRetryHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> T inputRetryHelper(Supplier<T> supplier) {
        T result = null;
        while (result == null) {
            result = tryExecuteSupplier(supplier);
        }
        return result;
    }

    private static <T> T tryExecuteSupplier(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
        return null;
    }
}
