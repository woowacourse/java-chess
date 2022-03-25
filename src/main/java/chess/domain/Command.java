package chess.domain;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static boolean isEnd(String command) {
        return Command.END.value.equals(command);
    }

    public static boolean isStart(String command) {
        return Command.START.value.equals(command);
    }

    public static boolean isMove(String command) {
        return command.startsWith(Command.MOVE.value);
    }
}
