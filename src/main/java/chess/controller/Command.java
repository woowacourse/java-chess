package chess.controller;

import java.util.Arrays;
import java.util.List;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private static final int MOVE_COMMAND_INPUT_SIZE = 3;
    private static final int DEFAULT_COMMAND_INPUT_SIZE = 1;
    private static final int COMMAND_INDEX = 0;

    private final String command;

    Command(final String command) {
        this.command = command;
    }

    public static Command from(List<String> inputs) {
        final Command command = Arrays.stream(Command.values())
                .filter(value -> value.command.equals(inputs.get(COMMAND_INDEX)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("start, end, move, status 중에 입력해주세요."));
        command.validateCommand(inputs);
        return command;
    }

    private void validateCommand(final List<String> inputs) {
        if (this == MOVE && inputs.size() != MOVE_COMMAND_INPUT_SIZE) {
            throw new IllegalArgumentException("move source위치 target위치 형태로 입력해주세요. 예) move a2 a3");
        }
        if (this != MOVE && inputs.size() != DEFAULT_COMMAND_INPUT_SIZE) {
            throw new IllegalArgumentException("start, status, end는 명령어만 입력해주세요.");
        }
    }
}
