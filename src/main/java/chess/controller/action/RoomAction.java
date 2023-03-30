package chess.controller.action;

import chess.domain.game.ChessGame;
import chess.service.ChessService;
import java.util.List;

@FunctionalInterface
public interface RoomAction {

    ChessGame execute(final ChessService chessService, final List<String> roomCommand);
}
