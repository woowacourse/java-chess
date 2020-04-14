package chess.controller.dto;

public enum Command {

    CREATE,
    ENTER,
    START,
    END,
    MOVE,
    UNKNOWN;

    public static Command of(String command) {
        try {
            return valueOf(command.toUpperCase());
        } catch (IllegalArgumentException ie) {
            return UNKNOWN;
        }
    }
}
