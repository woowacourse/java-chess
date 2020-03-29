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
        return START.command.equals(command);
    }

    public static boolean isMove(String command) {
        return MOVE.command.equals(command);
    }

    public static boolean isStatus(String command) {
        return STATUS.command.equals(command);
    }

    public static boolean isEnd(String command) {
        return END.command.equals(command);
    }
}
