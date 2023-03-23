package chess.controller;

import java.util.Arrays;

public enum GameCommand {

    START("start"),
    END("end"),
    MOVE("move");

    private final String command;

    GameCommand(final String command) {
        this.command = command;
    }

    public static GameCommand from(final String input) {
        return Arrays.stream(values()).filter(gameCommand -> gameCommand.match(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 커맨드입니다."));
    }

    private boolean match(final String input) {
        return this.command.equalsIgnoreCase(input);
    }

    public boolean isEnd() {
        return this == END;
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isMove() {
        return this == MOVE;
    }
}
