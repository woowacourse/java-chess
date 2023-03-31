package controller.command;

import domain.ChessGame;

public final class EndCommand implements ControlCommand {

    private final ChessGame chessGame;

    EndCommand(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public void run() {
        chessGame.end();
    }
}
