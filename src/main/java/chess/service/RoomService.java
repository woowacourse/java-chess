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
        try {
            return Optional.of(roomDao.addRoom(room));
        } catch (SQLException e) {
            throw new IllegalArgumentException("방을 만들 수 없습니다.");
        }
    }

    public Optional<RoomDto> findRoomById(long id) {
        try {
            return roomDao.findById(id);
        } catch (SQLException e) {
            throw new IllegalArgumentException("방을 찾을 수 없습니다.");
        }
    }

    public Optional<RoomDto> findRoomByTitle(String title) {
        try {
            return roomDao.findByTitle(title);
        } catch (SQLException e) {
            throw new IllegalArgumentException("방을 찾을 수 없습니다.");
        }
    }

    public List<RoomDto> findLatestNRooms(int topN) {
        try {
            return roomDao.findLatestN(topN);
        } catch (SQLException e) {
            throw new IllegalArgumentException("방 리스트를 찾을 수 없습니다.");
        }
    }
}
