package techcourse.fp.chess.controller;

import java.util.Arrays;

public enum Command {
    START,
    END,
    MOVE,
    EMPTY;

    public static Command from(String inputText) {
        return Arrays.stream(values())
                .filter(command -> command.name().equalsIgnoreCase(inputText))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 커맨드를 입력하였습니다."));
    }
}
