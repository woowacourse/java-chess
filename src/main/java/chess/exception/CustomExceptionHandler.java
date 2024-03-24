package chess.exception;

public class CustomExceptionHandler {
    public static void handle(Runnable callback) {
        try {
            callback.run();
        } catch (InvalidCommandException commandException) {
            System.out.println("[COMMAND ERROR] " + commandException.getMessage());
            handle(callback);
        } catch (ImpossibleMoveException moveException) {
            System.out.println("[MOVE ERROR] " + moveException.getMessage());
            handle(callback);
        }
    }
}
