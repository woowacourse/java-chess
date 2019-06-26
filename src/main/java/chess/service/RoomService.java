package chess.service;

import chess.dao.RoomDao;
import chess.dto.RoomDto;

import java.sql.SQLException;
import java.util.List;

public class RoomService {
    private RoomDao roomDao;

    public RoomService(final RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public long latestId() throws SQLException {
        roomDao.add();
        return roomDao.getLatestId().orElseThrow(SQLException::new);
    }

    public void updateStatus(final long roomId,final String status, final String color) {
        roomDao.updateStatus(roomId, status,color);
    }

    public List<RoomDto> findAllByStatus(final String status) {
        return roomDao.findAllByStatus(status);
    }
}
