package chess.controller.game;

import chess.service.ChessGame;
import java.util.List;

@FunctionalInterface
public interface GameAction {
    GameAction EMPTY = (chessGame, commands) -> {
    };

    void execute(final ChessGame chessGame, final List<String> commands);
}
