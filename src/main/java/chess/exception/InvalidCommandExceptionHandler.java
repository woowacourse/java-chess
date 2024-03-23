package chess.exception;

import java.util.function.Supplier;

public class InvalidCommandExceptionHandler {
    public static <T> T handle(Supplier<T> callback) {
        try {
            return callback.get();
        } catch (InvalidCommandException e) {
            System.out.println("[ERROR] " + e.getMessage());
            return handle(callback);
        }
    }

    public static void handle(Runnable callback) {
        try {
            callback.run();
        } catch (InvalidCommandException e) {
            System.out.println("[ERROR] " + e.getMessage());
            handle(callback);
        }
    }
}
