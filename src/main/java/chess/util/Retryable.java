package chess.util;


import chess.view.OutputView;

import java.util.function.Supplier;


public class Retryable {

    private Retryable() {
    }

    public static <T> T retryWhenException(Supplier<T> supplier) {
        T result;

        do {
            result = checkIllegalArgumentException(supplier);
        } while (result == null);

        return result;
    }

    private static <T> T checkIllegalArgumentException(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return null;
        }
    }
}
