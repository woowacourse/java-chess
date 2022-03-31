package chess.controller;

import chess.domain.game.state.ChessGame;

public interface Command {
    ChessGame run(ChessGame chessGame);
}
