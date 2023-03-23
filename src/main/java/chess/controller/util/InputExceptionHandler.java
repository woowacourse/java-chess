package chess.controller.util;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class InputExceptionHandler {

    private final Consumer<Exception> exceptionConsumer;

    public InputExceptionHandler(final Consumer<Exception> exceptionConsumer) {
        this.exceptionConsumer = exceptionConsumer;
    }

    public <T> void retryExecuteIfInputIllegal(Supplier<T> inputSupplier, Consumer<T> action) {
        try {
            T input = inputSupplier.get();
            action.accept(input);
        } catch (final IllegalStateException | IllegalArgumentException exception) {
            exceptionConsumer.accept(exception);
            retryExecuteIfInputIllegal(inputSupplier, action);
        }
    }

}
