package chess.exception;

import java.util.function.Supplier;

public class ImpossibleMoveExceptionHandler {
    public static <T> T handle(Supplier<T> callback) {
        try {
            return callback.get();
        } catch (ImpossibleMoveException e) {
            System.out.println("[ERROR] " + e.getMessage());
            return handle(callback);
        }
    }
}
