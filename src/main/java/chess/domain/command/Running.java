package chess.domain.command;

import chess.controller.ChessController;

import java.util.function.BiConsumer;

public enum Running {
    END((chessController, command) -> chessController.end()),
    MOVE((ChessController::move)),
    STATUS(((chessController, command) -> chessController.status())),
    NONE(((chessController, command) -> chessController.except()));

    private final BiConsumer<ChessController, Command> runningAction;

    Running(BiConsumer<ChessController, Command> runningAction) {
        this.runningAction = runningAction;
    }

    public void accept(ChessController chessController, Command command) {
        runningAction.accept(chessController, command);
    }
}