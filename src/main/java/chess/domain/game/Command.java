package chess.domain.game;

import java.util.Arrays;

public enum Command {
    START("start", GameStatus.START, 1),
    MOVE("move", GameStatus.START, 3),
    END("end", GameStatus.IDLE, 1),
    ;

    private final String keyword;
    private final GameStatus status;
    private final int size;

    public static final String INVALID_COMMAND_MESSAGE = "올바른 명령어를 입력하세요.";

    Command(final String keyword, final GameStatus status, final int size) {
        this.keyword = keyword;
        this.status = status;
        this.size = size;
    }

    public static Command from(final String gameCommand) {
        return Arrays.stream(values())
                .filter(command -> command.keyword.equalsIgnoreCase(gameCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COMMAND_MESSAGE));
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
