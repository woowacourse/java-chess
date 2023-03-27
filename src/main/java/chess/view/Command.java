package chess.view;

import static chess.view.ErrorMessage.NO_COMMAND_ERROR_GUIDE_MESSAGE;

public enum Command {

    START("start"),
    MOVE("move"),
    END("end"),
    STATUS("status");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command findCommandByName(String input) {
        for (Command command : Command.values()) {
            if (input.startsWith(command.value)) {
                return command;
            }
        }
        throw new IllegalArgumentException(NO_COMMAND_ERROR_GUIDE_MESSAGE.getErrorMessage());
    }

    public static boolean isStart(Command command) {
        return command.equals(Command.START);
    }

    public static boolean isStatus(Command command) {
        return command.equals(Command.STATUS);
    }

    public static boolean isEnd(Command command) {
        return command.equals(Command.END);
    }

    public static boolean isMove(Command command) {
        return command.equals(Command.MOVE);
    }
}
