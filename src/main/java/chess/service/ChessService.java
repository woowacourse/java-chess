package chess.service;

import chess.persistence.DataSourceFactory;
import chess.persistence.dao.BoardStateDao;
import chess.persistence.dao.RoomDao;
import chess.persistence.dto.BoardStateDto;
import chess.persistence.dto.RoomDto;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChessService {

    private RoomDao roomDao;
    private BoardStateDao boardStateDao;

    public ChessService() {
        DataSource ds = new DataSourceFactory().createDataSource();
        roomDao = new RoomDao(ds);
        boardStateDao = new BoardStateDao(ds);
    }

    public Optional<Long> createRoom(RoomDto room) {
        try {
            return Optional.of(roomDao.addRoom(room));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<RoomDto> findById(long id) {
        try {
            return roomDao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<RoomDto> findRoomByTitle(String title) {
        try {
            return roomDao.findByTitle(title);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<RoomDto> findLatestNRooms(int topN) {
        try {
            return roomDao.findLatestN(topN);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }
}
