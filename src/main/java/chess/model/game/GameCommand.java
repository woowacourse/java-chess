package chess.model.game;

import java.util.Arrays;

public enum GameCommand {

    START("start"),
    END("end"),
    MOVE("move");

    private final String command;

    GameCommand(final String command) {
        this.command = command;
    }

    public static GameCommand findGameCommand(final String command) {
        return Arrays.stream(values()).filter(gameCommand -> gameCommand.command.equalsIgnoreCase(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어입니다."));
    }

    public boolean isMove() {
        return this == MOVE;
    }

    public boolean isStart() {
        return this == START;
    }
}
