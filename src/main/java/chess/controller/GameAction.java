package chess.controller;

import chess.domain.ChessGame;

import java.util.List;

@FunctionalInterface
public interface GameAction {
    void execute(List<String> commands, ChessGame chessGame);
}
