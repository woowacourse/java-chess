package chess.controller;

import chess.domain.ChessGame;

@FunctionalInterface
public interface GameAction {
    ChessGame execute(final Command command, ChessGame chessGame);
}
