package chess.controller;

import java.util.Arrays;
import java.util.List;

enum Command {
    START("start"),
    MOVE("move"),
    END("end"),
    ;

    public static final int MOVE_COMMAND_INDEX = 0;
    public static final int MOVE_SOURCE_INDEX = 1;
    public static final int MOVE_TARGET_INDEX = 2;
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int END_COMMAND_SIZE = 1;

    private final String value;

    Command(final String value) {
        this.value = value;
    }

    public static Command createInitCommand(final String inputCommand) {
        return getCommand(START, inputCommand);
    }

    public static Command createPlayingCommand(final String inputCommand) {
        return getCommand(MOVE, inputCommand);
    }

    private static Command getCommand(final Command possibleCommand, final String inputCommand) {
        return Arrays.stream(values())
                .filter(command -> command == possibleCommand || command == END)
                .filter(command -> command.value.equals(inputCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        possibleCommand.value + " 또는 " + END.value + " 를 입력해주세요."));
    }

    public static void validateCommandSize(final List<String> commands) {
        int size = commands.size();
        if (!(size == END_COMMAND_SIZE || size == MOVE_COMMAND_SIZE)) {
            throw new IllegalArgumentException("명령어 형식을 확인해주세요.");
        }
    }
}
