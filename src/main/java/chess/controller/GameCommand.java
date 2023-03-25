package chess.controller;

import java.util.Arrays;

public enum GameCommand {

    START("start"),
    LOAD("load"),
    MOVE("move"),
    STATUS("status"),
    END("end");

    private final String command;

    GameCommand(final String command) {
        this.command = command;
    }

    public static GameCommand findGameCommand(final String command) {
        return Arrays.stream(values())
                .filter(gameCommand -> gameCommand.command.equalsIgnoreCase(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어입니다."));
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isLoad() {
        return this == LOAD;
    }

    public boolean isMove() {
        return this == MOVE;
    }

    public boolean isStatus() {
        return this == STATUS;
    }

    public boolean isEnd() {
        return this == END;
    }
}
