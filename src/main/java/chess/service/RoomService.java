package chess.service;

import chess.persistence.AbstractDataSourceFactory;
import chess.persistence.dao.RoomDao;
import chess.persistence.dto.RoomDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RoomService {
    private RoomDao roomDao;

    public RoomService(AbstractDataSourceFactory dataSourceFactory) {
        this.roomDao = new RoomDao(dataSourceFactory.createDataSource());
    }

    public Optional<Long> createRoom(RoomDto room) {
        return Optional.of(roomDao.addRoom(room));
    }

    public Optional<RoomDto> findRoomById(long id) {
        return roomDao.findById(id);
    }

    public Optional<RoomDto> findRoomByTitle(String title) {
        return roomDao.findByTitle(title);
    }

    public List<RoomDto> findLatestNRooms(int topN) {
        return roomDao.findLatestN(topN);
    }
}
