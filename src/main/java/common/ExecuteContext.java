package common;

public final class ExecuteContext {

    public static <T> T workWithExecuteStrategy(final ExecuteStrategy<T> executeStrategy) {
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

    public static <T> void repeatWithDefaultAndRepeatableJudgeStrategy(final ExecuteStrategy<T> defaultStrategy,
        final ExecuteStrategy<Boolean> repeatableStrategy) {
        do {
            defaultStrategy.execute();
        } while (repeat(repeatableStrategy));
    }

    private static <T> T repeat(final ExecuteStrategy<T> repeatableStrategy) {
        try {
            return repeatableStrategy.execute();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return repeat(repeatableStrategy);
        }
    }
}
