package chess.controller.util;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class InputExceptionHandler {

    private final Consumer<Exception> exceptionConsumer;

    public InputExceptionHandler(final Consumer<Exception> exceptionConsumer) {
        this.exceptionConsumer = exceptionConsumer;
    }

    public <T, R> R retryExecuteIfInputIllegal(Supplier<T> inputSupplier, Function<T, R> action) {
        try {
            T input = inputSupplier.get();
            return action.apply(input);
        } catch (final IllegalStateException | IllegalArgumentException exception) {
            exceptionConsumer.accept(exception);
            return retryExecuteIfInputIllegal(inputSupplier, action);
        }
    }

}
