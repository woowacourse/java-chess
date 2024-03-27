package controller;

import java.util.function.Supplier;
import view.OutputView;

public class ExceptionHandler {

    private ExceptionHandler() {
        throw new UnsupportedOperationException();
    }

    public static <T> T handleInputWithRetry(Supplier<T> callback) {
        try {
            return callback.get();
        } catch (Exception e) {
            OutputView.printExceptionMessage(e.getMessage());
            return handleInputWithRetry(callback);
        }
    }
}
