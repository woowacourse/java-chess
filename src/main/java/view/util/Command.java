package view.util;

public enum Command {

    START("start"),
    MOVE("move"),
    END("end");

    private final String identifier;

    Command(String identifier) {
        this.identifier = identifier;
    }

    public static boolean isStartCommand(String identifier) {
        return START.identifier.equals(identifier);
    }

    public static boolean isMoveCommand(String identifier) {
        return MOVE.identifier.equals(identifier);
    }

    public static boolean isEndCommand(String identifier) {
        return END.identifier.equals(identifier);
    }
}
