package chess.controller;

import java.util.Arrays;

public enum ChessStatus {
    RUN("start"),
    END("end"),
    ;

    private final String command;

    ChessStatus(String command) {
        this.command = command;
    }

    public static ChessStatus from(String inputCommand) {
        return Arrays.stream(ChessStatus.values())
                .filter(it -> it.command.equals(inputCommand))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 명령어는 존재하지 않습니다."));
    }

    public boolean isEnd() {
        return this == END;
    }
}
