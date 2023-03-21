package common;

public class ExecuteContext {

    public static <T> T repeatableExecute(final ExecuteStrategy<T> executeStrategy) {
        T result = null;
        while (result == null) {
            try {
                result = executeStrategy.execute();
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return result;
    }

    public static <T> void repeatableExecute(final ExecuteStrategy<T> defaultStrategy,
        final ExecuteStrategy<Boolean> repeatableStrategy) {
        do {
            defaultStrategy.execute();
        } while (repeatableExecute(repeatableStrategy));
    }
}
