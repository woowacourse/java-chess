package chess.controller;

import chess.view.OutputView;

import java.util.function.Supplier;

public class ErrorController {

    public <T> T RetryIfThrowsException(final Supplier<T> strategy) {
        T result = tryCatchStrategy(strategy);
        while (result == null) {
            result = tryCatchStrategy(strategy);
        }
        return result;
    }

    public void tryCatchStrategy(final Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
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

}
