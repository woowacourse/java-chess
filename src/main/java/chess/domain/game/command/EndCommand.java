package chess.domain.game.command;

import chess.domain.game.ChessGame;

public class EndCommand implements ChessGameCommand {

    @Override
    public void execute(ChessGame chessGame) {
        chessGame.endGame();
    }
}
