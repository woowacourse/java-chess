package chess.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move");

    private static final int MOVE_COMMAND_INPUT_SIZE = 3;
    private static final int COMMAND_INDEX = 0;

    private final String command;

    Command(final String command) {
        this.command = command;
    }

    public static Command from(final List<String> gameCommand) {
        Command command = findCommandBy(gameCommand.get(COMMAND_INDEX));
        validateMove(command, gameCommand);
        return command;
    }

    private static Command findCommandBy(final String input) {
        return Arrays.stream(Command.values())
                .filter(value -> value.command.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("start, end, move 중에 입력해주세요."));
    }

    private static void validateMove(final Command command, final List<String> inputs) {
        if (command == MOVE && inputs.size() != MOVE_COMMAND_INPUT_SIZE) {
            throw new IllegalArgumentException("move source위치 target위치 형태로 입력해주세요. 예) move a2 a3");
        }
    }

    public void validateContains(final Command... commands) {
        final List<Command> correctCommands = Arrays.stream(commands)
                .collect(Collectors.toList());

        if (!correctCommands.contains(this)) {
            throw new IllegalArgumentException(
                    "올바른 command를 입력해주세요. 게임 시작은 start로, 게임 진행은 move로, 게임 종료는 end로 할 수 있습니다.");
        }
    }
}
