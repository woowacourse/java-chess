package chess.controller.action;

import chess.domain.game.ChessGame;
import java.util.List;

@FunctionalInterface
public interface GameAction {

    void execute(final ChessGame chessGame, final List<String> command);
}
