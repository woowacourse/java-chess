package chess.game;

import java.util.Arrays;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move"),
    ;

    private final String keyword;

    Command(final String keyword) {
        this.keyword = keyword;
    }

    public static Command from(final String gameCommand) {
        return Arrays.stream(values())
                .filter(command -> command.keyword.equalsIgnoreCase(gameCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어입니다."));
    }
}
