package chess.controller;

import chess.domain.ChessGame;
import java.util.List;

@FunctionalInterface
public interface GameAction {
    void execute(int gameId, List<String> arguments, ChessGame chessGame);
}
