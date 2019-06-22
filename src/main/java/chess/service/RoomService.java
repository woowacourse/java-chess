package chess.service;

import chess.dao.RoomDao;

import java.sql.SQLException;

public class RoomService {
    private RoomDao roomDao;

    public RoomService(final RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public long latestId() throws SQLException {
        roomDao.add();
        return roomDao.getLatestId().orElseThrow(SQLException::new);
    }

    public void updateStatus(final long roomId, final String color) {
        roomDao.updateStatus(roomId, color);
    }
}
