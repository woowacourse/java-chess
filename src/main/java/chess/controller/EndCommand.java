package chess.controller;

import chess.domain.game.state.ChessGame;

public class EndCommand implements Command {
    @Override
    public ChessGame run(ChessGame chessGame, String fromPosition, String toPosition) {
        return chessGame.end();
    }
}
