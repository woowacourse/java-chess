package chess.command;

public enum Command {
    START,
    END,
    MOVE,
    STATUS,
    UNKNOWN;

    public static Command of(String command) {
        try {
            return valueOf(command.toUpperCase());
        } catch (IllegalArgumentException ie) {
            return UNKNOWN;
        }
    }
}
