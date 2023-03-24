package chess.domain.game;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Command {
    START(GameStatus.START, 1),
    MOVE(GameStatus.START, 3),
    STATUS(GameStatus.START, 1),
    END(GameStatus.IDLE, 1),
    ;

    public static final String INVALID_COMMAND_MESSAGE = "올바른 명령어를 입력하세요.";

    private final GameStatus status;
    private final int size;

    Command(final GameStatus status, final int size) {
        this.status = status;
        this.size = size;
    }

    public static Command from(final String gameCommand) {
        return Arrays.stream(values())
                .filter(command -> command.name().equalsIgnoreCase(gameCommand))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(INVALID_COMMAND_MESSAGE));
    }

    public static void validateCommandSize(final int given, final Command expect) {
        if (given != expect.size) {
            throw new IllegalArgumentException(INVALID_COMMAND_MESSAGE);
        }
    }

    public GameStatus getStatus() {
        return status;
    }
}
