package chess.domain;

import java.util.Arrays;
import java.util.regex.Pattern;

public enum GameCommand {

    START("start"),
    MOVE("move [a-h][1-9] [a-h][1-9]"),
    END("end"),
    STATUS("status"),
    ;

    private static final String WRONG_COMMAND_MESSAGE = "잘못된 명령어를 입력하였습니다.";

    private final String command;

    GameCommand(String command) {
        this.command = command;
    }

    public static GameCommand findByInput(final String command) {
        return Arrays.stream(GameCommand.values())
            .filter(it -> Pattern.matches(it.command, command))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(WRONG_COMMAND_MESSAGE));
    }
}
