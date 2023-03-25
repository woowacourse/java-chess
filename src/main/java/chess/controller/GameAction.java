package chess.controller;

import chess.domain.ChessGame;

import java.util.List;

@FunctionalInterface
public interface GameAction {
    void execute(final List<String> commands, final ChessGame chessGame);
}
