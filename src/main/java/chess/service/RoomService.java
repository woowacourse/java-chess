package chess.service;

import chess.controller.dto.RoomDto;
import chess.service.dao.RoomDao;
import spark.Request;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RoomService {

    private final Connection connection;
    private final RoomDao roomDao;

    public RoomService(final Connection connection) {
        this.connection = connection;
        this.roomDao = new RoomDao(connection);
    }

    public Long save(final Request req) throws SQLException {
        final Long roomId = System.currentTimeMillis();
        roomDao.save(roomName(req), roomId);
        return roomId;
    }

    public List<RoomDto> loadList() throws SQLException {
        return roomDao.load();
    }

    public Long roomId(final Request req) throws SQLException {
        return roomDao.id(roomName(req));
    }

    public RoomDto room(final Long roomId) throws SQLException {
        final RoomDto roomDto = new RoomDto();
        roomDto.setId(roomId);
        roomDto.setName(roomDao.name(roomId));
        return roomDto;
    }

    private String roomName(final Request req) {
        return req.params(":roomName");
    }
}
