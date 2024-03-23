package chess.exception;

public class ImpossibleMoveExceptionHandler {
    public static void handle(Runnable callback) {
        try {
            callback.run();
        } catch (ImpossibleMoveException e) {
            System.out.println("[ERROR] " + e.getMessage());
            handle(callback);
        }
    }
}
