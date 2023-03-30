package chess.controller;

import chess.view.OutputView;

import java.util.function.Supplier;

public final class ErrorController {

    public <T> T retryIfThrowsException(final Supplier<T> strategy) {
        T result = tryCatchStrategy(strategy);
        while (result == null) {
            result = tryCatchStrategy(strategy);
        }
        return result;
    }

    public void retryIfThrowsException(final Runnable runnable) {
        boolean isSuccess = tryCatchStrategy(runnable);
        while (!isSuccess) {
            isSuccess = tryCatchStrategy(runnable);
        }
    }

    public <T> T tryCatchStrategy(final Supplier<T> strategy) {
        try {
            return strategy.get();
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
        }
        return null;
    }

    public boolean tryCatchStrategy(final Runnable runnable) {
        try {
            runnable.run();
            return true;
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return false;
        }
    }

}
