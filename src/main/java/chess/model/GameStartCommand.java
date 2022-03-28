package chess.model;

import java.util.Arrays;

public enum GameStartCommand {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String commandLine;

    GameStartCommand(final String commandLine) {
        this.commandLine = commandLine;
    }

    public static GameStartCommand findCommand(final String commandLine) {
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
}
