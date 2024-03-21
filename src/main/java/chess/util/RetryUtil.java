package chess.util;

import java.util.Optional;
import java.util.function.Supplier;

public class RetryUtil {

    public static <T> T retryUntilNoException(Supplier<T> supplier) {
        Optional<T> optional = Optional.empty();

        while (optional.isEmpty()) {
            optional = checkAriseException(supplier);
        }

        return optional.get();
    }

    private static <T> Optional<T> checkAriseException(Supplier<T> supplier) {
        try {
            return Optional.of(supplier.get());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }
}