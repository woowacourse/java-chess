package chess.controller;

public enum Command {
    START("start"),
    MOVE("move"),
    STATUS("status"),
    END("end");

    private String command;

    Command(String command) {
        this.command = command;
    }

    public static boolean isStart(String command) {
        return command.startsWith(START.command);
    }

    public static boolean isMove(String command) {
        return command.startsWith(MOVE.command);
    }

    public static boolean isStatus(String command) {
        return command.startsWith(STATUS.command);
    }

    public static boolean isEnd(String command) {
        return command.startsWith(END.command);
    }
}
