package chess.controller.command;

import chess.domain.ChessGame;

public class EndCommand implements Command {

    @Override
    public void execute(ChessGame chessGame) {
        chessGame.endGame();
    }
}
