package chess.controller.console.exception;

public class Exception {
    public static void printCommandException(IllegalArgumentException e){
        System.err.println(e.getMessage());
    }

    public static void printTurnException(RuntimeException e) {
        System.err.println(e.getMessage());
    }
}
