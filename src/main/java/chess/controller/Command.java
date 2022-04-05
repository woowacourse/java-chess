package chess.controller;

import java.util.Arrays;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status")
    ;

    private static final int COMMAND_INDEX = 0;
    private static final String COMMAND_SPLIT = " ";

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static String from(String commandText) {
        final String[] splitCommand = commandText.split(COMMAND_SPLIT);
        validateCommand(splitCommand);
        return commandText;
    }

    private static Command validateCommand(String[] splitCommand) {
        return Arrays.stream(values())
                .filter(command -> command.value.equals(splitCommand[COMMAND_INDEX]))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령입니다."));
    }

    public String getValue() {
        return value;
    }
}
