package chess.controller;

import chess.domain.ChessRunner;

import java.util.Arrays;
import java.util.Optional;

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

    public static Optional<Command> of(final String command) {
        return Arrays.stream(values())
                .filter(c -> command.contains(c.command))
                .findFirst();
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

    public void execute(ChessRunner chessRunner, String commands) {
        this.gameController.execute(chessRunner, commands);
    }
}
