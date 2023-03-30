package controller.command;

import domain.ChessGame;

public final class StartCommand implements ControlCommand {

    private final ChessGame chessGame;

    StartCommand(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public void run() {
        chessGame.ready();
        chessGame.makeBoard();
    }
}
