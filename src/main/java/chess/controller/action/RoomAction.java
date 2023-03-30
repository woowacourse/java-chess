package chess.controller.action;

import chess.domain.game.ChessGame;
import java.util.List;

@FunctionalInterface
public interface RoomAction {

    ChessGame execute(final List<String> roomCommand);
}
