package chess.controller;

import java.util.Arrays;

public enum Command {
    START("start", new StartController()),
    MOVE("move", new MoveController()),
    STATUS("status", new StatusController()),
    END("end", new EndController());

    private final String command;
    private final GameController gameController;

    Command(String command, GameController gameController) {
        this.command = command;
        this.gameController = gameController;
    }

    public static Command of(final String command) {
        return Arrays.stream(values())
                .filter(c -> command.contains(c.command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어를 입력하였습니다."));
    }

    public boolean isStart() {
        return this == START;
    }

    public GameController getGameController() {
        return this.gameController;
    }

    public boolean isEnd() {
        return this == END;
    }
}
