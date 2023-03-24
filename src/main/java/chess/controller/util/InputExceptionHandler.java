package chess.controller.util;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class InputExceptionHandler {

    private final Consumer<Exception> exceptionConsumer;

    public InputExceptionHandler(final Consumer<Exception> exceptionConsumer) {
        this.exceptionConsumer = exceptionConsumer;
    }

    public <T, U, R> R retryExecuteIfInputIllegal(Supplier<U> inputSupplier, T controller, BiFunction<T, U, R> action) {
        try {
            U input = inputSupplier.get();
            return action.apply(controller, input);
        } catch (final IllegalStateException | IllegalArgumentException exception) {
            exceptionConsumer.accept(exception);
            return retryExecuteIfInputIllegal(inputSupplier, controller, action);
        }
    }

}
