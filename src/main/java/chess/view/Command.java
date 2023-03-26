package chess.view;

public enum Command {

    START("start"),
    MOVE("move"),
    END("end"),
    STATUS("status"),
    NEW_GAME("1"),
    LOAD_GAME("2")

    ;

    private final String value;

    Command(final String value) {
        this.value = value;
    }

    public static boolean isEnd(String command) {
        return END.value.equals(command);
    }

    public static boolean isMove(String command) {
        return MOVE.value.equals(command);
    }

    public static boolean isNotStart(String command) {
        return !START.value.equals(command);
    }

    public static boolean isNotAppropriate(String command) {
        return START.value.equals(command) || isEnd(command);
    }

    public static boolean isStatus(final String command) {
        return STATUS.value.equals(command);
    }

    public static boolean isNewGame(final String command) {
        return NEW_GAME.value.equals(command);
    }

    public static boolean isLoadGame(final String command) {
        return LOAD_GAME.value.equals(command);
    }
}
