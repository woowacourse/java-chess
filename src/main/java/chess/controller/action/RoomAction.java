package chess.controller.action;

import chess.domain.game.ChessGame;
import chess.repository.dao.ChessGameDao;
import chess.repository.dao.PieceDao;
import java.util.List;

@FunctionalInterface
public interface RoomAction {

    ChessGame execute(final List<String> roomCommand);
}
