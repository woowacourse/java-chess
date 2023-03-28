package common;

import java.util.function.Supplier;

public final class ExecuteContext {

    public static <T> T repeatableExecute(final Supplier<T> executeStrategy) {
        T result = null;
        while (result == null) {
            try {
                result = executeStrategy.get();
            } catch (final RuntimeException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return result;
    }

    public static <T> void repeatableExecute(final Supplier<T> defaultStrategy,
        final Supplier<Boolean> repeatableStrategy) {
        while (true) {
            boolean result = repeatableExecute(repeatableStrategy);
            if (!result) {
                break;
            }
            defaultStrategy.get();
        }
    }
}
