package chess.room;

import chess.domain.board.Square;

import java.sql.SQLException;
import java.util.List;

public class RoomService {
    private final NotationDao notationDao;
    private final RoomDao roomDao;
    private Room room;

    public RoomService() {
        this.roomDao = new RoomDao();
        this.notationDao = new NotationDao();
    }

    public boolean isRoom(String name) throws SQLException {
        try {
            room = roomDao.FindByName(name);
            return true;
        } catch (SQLException e) {
            room = roomDao.addRoom(name);
            return false;
        }
    }

    public List<Move> getNotation() throws SQLException {
        final int roomId = room.getRoomId();
        this.room.putTurn(notationDao.findLastTurn(roomId));
        return notationDao.findByRoomId(roomId);
    }

    public void updateMove(final Square source, final Square target) throws SQLException {
        notationDao.addNotation(room, new Move(source, target));
        room.nextTurn();
    }

    public void deleteNotation() throws SQLException {
        room.reset();
        notationDao.deleteByRoomId(room.getRoomId());
    }

    public void deleteRoom() throws SQLException {
        final int roomId = room.getRoomId();
        notationDao.deleteByRoomId(roomId);
        roomDao.deleteRoom(roomId);
    }
}
