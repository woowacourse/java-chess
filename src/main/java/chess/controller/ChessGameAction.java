package chess.controller;

import chess.service.ChessGame;
import java.util.List;

@FunctionalInterface
public interface ChessGameAction {
    ChessGameAction EMPTY = (chessGame, commands) -> {
    };

    void execute(final ChessGame chessGame, final List<String> commands);
}
