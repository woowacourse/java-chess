package chess.service;

import chess.domain.piece.Color;
import chess.domain.position.Move;
import chess.repository.MoveDao;
import chess.repository.RoomDao;
import java.util.List;

public class GameService {

    private final RoomDao roomDao;
    private final MoveDao moveDao;

    public GameService(RoomDao roomDao, MoveDao moveDao) {
        this.roomDao = roomDao;
        this.moveDao = moveDao;
    }

    public List<Move> findMoves(long roomId) {
        return moveDao.findAllByRoomId(roomId);
    }

    public void create(long roomId, String source, String target) {
        moveDao.save(roomId, Move.from(source, target));
    }

    public void updateWinner(long roomId, Color winner) {
        roomDao.updateWinner(roomId, winner);
    }
}
