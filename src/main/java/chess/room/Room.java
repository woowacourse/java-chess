package chess.room;

import chess.domain.board.Square;

import java.sql.SQLException;
import java.util.List;

public class Room {
    private final int roomId;
    private int turn;

    public Room(final int roomId) {
        this.roomId = roomId;
        turn = 1;
    }

    public List<Move> getNotation() throws SQLException {
        Notation notation = NotationDao.findByRoomId(roomId);
        this.turn = NotationDao.findLastTurn(roomId) + 1;
        return notation.getNotation();
    }

    public void updateMove(final Square source, final Square target) throws SQLException {
        NotationDao.addNotation(roomId, turn, new Move(source, target));
        turn++;
    }

    public void deleteNotation() throws SQLException {
        turn = 1;
        NotationDao.deleteByRoomId(roomId);
    }

    public void deleteRoom() throws SQLException {
        NotationDao.deleteByRoomId(roomId);
        RoomDao.deleteRoom(roomId);
    }
}
