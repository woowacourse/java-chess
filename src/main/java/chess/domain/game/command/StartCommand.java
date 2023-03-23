package chess.domain.game.command;

import chess.domain.game.state.ChessGame;

public class StartCommand implements ChessGameCommand {

    @Override
    public ChessGame execute(ChessGame chessGame) {
        return chessGame.startGame();
    }
}
