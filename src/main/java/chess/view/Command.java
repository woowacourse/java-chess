package chess.view;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    STATUS("status"),
    MOVE("move");

    private final String command;

    Command(final String command) {
        this.command = command;
    }

    public static Command from(final String inputCommand) {
        return Arrays.stream(Command.values())
                .filter(gameState -> gameState.command.equals(inputCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("start, end, move 만 입력할 수 있습니다."));
    }
}
