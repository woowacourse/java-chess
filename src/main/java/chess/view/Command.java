package chess.view;

public enum Command {
    START("start"),
    MOVE("move"),
    END("end");

    private static final String NO_COMMAND_ERROR_GUIDE_MESSAGE = "일치하는 명령이 없습니다.";
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
        throw new IllegalArgumentException(NO_COMMAND_ERROR_GUIDE_MESSAGE);
    }
}
