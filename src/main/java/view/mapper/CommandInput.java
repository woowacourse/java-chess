package view.mapper;

import domain.command.Command;
import java.util.Arrays;
import java.util.List;

public enum CommandInput {

    START(Command.START, "^start$"),
    MOVE(Command.MOVE, "^move [a-h][1-8] [a-h][1-8]$"),
    END(Command.END, "^end$");

    public static final int MOVE_COMMAND_SOURCE_POSITION_INDEX = 1;
    public static final int MOVE_COMMAND_TARGET_POSITION_INDEX = 2;
    private static final String MOVE_COMMAND_DELIMITER = " ";
    private final Command command;
    private final String input;

    CommandInput(Command command, String input) {
        this.command = command;
        this.input = input;
    }

    public static void validateCommand(String input) {
        if (Arrays.stream(values())
                .noneMatch(commandInput -> input.matches(commandInput.input))) {
            throw new IllegalArgumentException("[ERROR] 올바른 명령어를 입력해주세요.");
        }
    }

    public static Command asCommand(String input) {
        return Arrays.stream(values())
                .filter(commandInput -> input.matches(commandInput.input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 올바른 명령어를 입력해주세요."))
                .command;
    }

    public static List<String> extractPositions(String rawCommand) {
        if (!rawCommand.matches(CommandInput.MOVE.input)) {
            throw new IllegalArgumentException("[SERVER_ERROR] 이동 명령어만 분리가 가능합니다.");
        }
        return Arrays.stream(rawCommand.split(MOVE_COMMAND_DELIMITER))
                .toList()
                .subList(MOVE_COMMAND_SOURCE_POSITION_INDEX, MOVE_COMMAND_TARGET_POSITION_INDEX + 1);
    }
}
