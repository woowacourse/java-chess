package chess.util;

import static chess.view.OutputView.printError;

import java.util.function.Supplier;

public final class ExceptionHandler {

    public static <T> T repeatUntilValidInput(final Supplier<T> reader) {
        try {
            return reader.get();
        } catch (IllegalArgumentException e) {
            printError(e.getMessage());
            return repeatUntilValidInput(reader);
        }
    }
}
