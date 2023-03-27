package chess.controller;

import java.util.Arrays;

public enum StartCommandType {

    START,
    END,
    ;

    public static StartCommandType from(final String command) {
        return Arrays.stream(values())
                .filter(startCommandType -> startCommandType.name()
                        .equalsIgnoreCase(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("게임 시작 전에 입력할 수 없는 명령어입니다."));
    }
}
