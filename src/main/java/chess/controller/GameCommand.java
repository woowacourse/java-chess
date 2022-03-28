package chess.controller;

import java.util.Arrays;

public enum GameCommand {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String commandLine;

    GameCommand(final String commandLine) {
        this.commandLine = commandLine;
    }

    public static GameCommand findCommand(final String commandLine) {
        return Arrays.stream(values())
                .filter(gameStartCommand -> gameStartCommand.commandLine.equals(commandLine))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("잘못된 게임 시작 커맨드입니다. %s", commandLine)));
    }

    public boolean isStart() {
        return this.equals(START);
    }

    public boolean isMove() {
        return this.equals(MOVE);
    }

    public boolean isStatus() {
        return this.equals(STATUS);
    }

    public boolean isEnd() {
        return this.equals(END);
    }
}
