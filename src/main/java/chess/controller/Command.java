package chess.controller;

import java.util.Arrays;
import java.util.List;

enum Command {
    START("start"),
    LOAD("load"),
    MOVE("move"),
    STATUS("status"),
    END("end"),
    ;

    public static final int MOVE_COMMAND_INDEX = 0;
    public static final int MOVE_SOURCE_INDEX = 1;
    public static final int MOVE_TARGET_INDEX = 2;
    public static final int MOVE_COMMAND_SIZE = 3;
    public static final int END_COMMAND_SIZE = 1;

    private final String value;

    Command(final String value) {
        this.value = value;
    }

    public static Command createInitCommand(final String inputCommand) {
        return getCommand(START, LOAD, inputCommand);
    }

    public static Command createPlayingCommand(final String inputCommand) {
        return getCommand(MOVE, STATUS, inputCommand);
    }

    private static Command getCommand(final Command firstCommand, final Command secondCommand, final String inputCommand) {
        return Arrays.stream(values())
                .filter(command -> command == firstCommand || command == secondCommand || command == END)
                .filter(command -> command.value.equals(inputCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        firstCommand.value + ", " + secondCommand.value + ", " + END.value + " 중 하나를 입력해주세요.")
                );
    }

    public static void validateMoveCommandForm(final List<String> commands) {
        final int size = commands.size();
        if (!(size == MOVE_COMMAND_SIZE || size == END_COMMAND_SIZE)) {
            throw new IllegalArgumentException("명령어 형식을 확인해주세요.");
        }
    }
}
