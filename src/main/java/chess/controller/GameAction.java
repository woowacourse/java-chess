package chess.controller;

import chess.domain.chessgame.ChessGame;

@FunctionalInterface
public interface GameAction {
    ChessGame execute(final Command command, ChessGame chessGame);
}
