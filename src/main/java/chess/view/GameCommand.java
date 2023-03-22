package chess.view;

public enum GameCommand {
    START("start"),
    END("end"),
    MOVE("move");

    private final String command;

    GameCommand(String command) {
        this.command = command;
    }

    public static boolean isStart(String command) {
        return START.command.equals(command);
    }

    public static boolean isEnd(String command) {
        return END.command.equals(command);
    }

    public static boolean isMove(String command) {
        return MOVE.command.equals(command);
    }

    public String getCommand() {
        return command;
    }
}
