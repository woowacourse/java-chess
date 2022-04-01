package chess.domain.command;

import chess.domain.state.State;
import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUE("status"),
    ;

    private static final String NO_SUCH_INPUT_ERROR_MESSAGE = "없는 명령어를 입력했습니다";
    private static final String DELIMITER = " ";

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static CommandState getCommandState(String input) {
        Command command = find(input.split(DELIMITER)[0]);
        if (command == START) {
            return new Start();
        }
        if (command == END) {
            return new End();
        }
        if (command == MOVE) {
            return new Move();
        }
        if (command == STATUE) {
            return new Status();
        }
        throw new IllegalArgumentException(NO_SUCH_INPUT_ERROR_MESSAGE);
    }

    public static Command find(String input) {
        return Arrays.stream(Command.values())
                .filter(value -> value.command.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_SUCH_INPUT_ERROR_MESSAGE));
    }
}
