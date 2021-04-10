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
        final boolean isDuplicated = roomDao.load().stream()
                .map(roomDto -> roomDto.getName())
                .anyMatch(name -> name.equals(roomName));

        if (isDuplicated) {
            throw new IllegalArgumentException("중복된 방 이름입니다.");
        }

        final Long roomId = System.currentTimeMillis();
        roomDao.save(roomName, roomId);
        return roomId;
    }

    public void delete(final Long roomId) throws SQLException {
        roomDao.delete(roomId);
    }

    public List<RoomDto> loadList() throws SQLException {
        return roomDao.load();
    }

    public RoomDto room(final Long roomId) throws SQLException {
        return new RoomDto(roomId, roomDao.name(roomId));
    }
}
