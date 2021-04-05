package chess.service;

import chess.controller.dto.RoomDto;
import chess.service.dao.RoomDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RoomService {

    private final RoomDao roomDao;

    public RoomService(final Connection connection) {
        this.roomDao = new RoomDao(connection);
    }

    public Long save(final String roomName) throws SQLException {
        final Long roomId = System.currentTimeMillis();
        roomDao.save(roomName, roomId);
        return roomId;
    }

    public List<RoomDto> loadList() throws SQLException {
        return roomDao.load();
    }

    public Long roomId(final String roomName) throws SQLException {
        return roomDao.id(roomName);
    }

    public RoomDto room(final Long roomId) throws SQLException {
        final RoomDto roomDto = new RoomDto();
        roomDto.setId(roomId);
        roomDto.setName(roomDao.name(roomId));
        return roomDto;
    }
}
