package chess.persistence.dao;

import chess.persistence.MySqlDataSource;
import chess.persistence.SQLiteDataSource;
import chess.persistence.dto.RoomDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class RoomDaoTest {
    RoomDao roomDao;

    @BeforeEach
    void init() {
        try {
            new MySqlDataSource().getConnection();
            roomDao = new RoomDao(new MySqlDataSource());
        } catch (SQLException e) {
            roomDao = new RoomDao(new SQLiteDataSource());
        }
    }

    @Test
    void insertAndFind() throws SQLException {
        RoomDto room = new RoomDto();
        room.setTitle("아무나");
        long insertedId = roomDao.addRoom(room);
        RoomDto found = roomDao.findById(insertedId).get();
        assertThat(found.getTitle()).isEqualTo(room.getTitle());
        roomDao.deleteById(insertedId);
    }

    @Test
    void findByTitle() throws SQLException {
        RoomDto room = new RoomDto();
        room.setTitle("으어어어");
        long insertedId = roomDao.addRoom(room);
        Optional<RoomDto> maybeFound = roomDao.findByTitle("으어어어");
        assertThat(maybeFound.isPresent()).isTrue();
        roomDao.deleteById(insertedId);
    }

    @Test
    void deleteById() throws SQLException {
        RoomDto room = new RoomDto();
        room.setTitle("지워져야함");
        long insertedId = roomDao.addRoom(room);
        int affected = roomDao.deleteById(insertedId);
        assertThat(affected).isEqualTo(1);
        assertThat(roomDao.findById(insertedId).isPresent()).isFalse();
    }
}
