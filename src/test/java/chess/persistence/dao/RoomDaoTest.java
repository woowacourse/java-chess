package chess.persistence.dao;

import chess.persistence.MySqlDataSource;
import chess.persistence.dto.RoomDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class RoomDaoTest {
    RoomDao dao;

    @BeforeEach
    void init() {
        dao = new RoomDao(new MySqlDataSource());
    }

    @Test
    void insertAndFind() throws SQLException {
        RoomDto room = new RoomDto();
        room.setTitle("아무나");
        long insertedId = dao.addRoom(room);
        RoomDto found = dao.findById(insertedId).get();
        assertThat(found.getTitle()).isEqualTo(room.getTitle());
        dao.deleteById(insertedId);
    }

    @Test
    void findByTitle() throws SQLException {
        RoomDto room = new RoomDto();
        room.setTitle("으어어어");
        long insertedId = dao.addRoom(room);
        Optional<RoomDto> maybeFound = dao.findByTitle("으어어어");
        assertThat(maybeFound.isPresent()).isTrue();
        dao.deleteById(insertedId);
    }

    @Test
    void deleteById() throws SQLException {
        RoomDto room = new RoomDto();
        room.setTitle("지워져야함");
        long insertedId = dao.addRoom(room);
        int affected = dao.deleteById(insertedId);
        assertThat(affected).isEqualTo(1);
        assertThat(dao.findById(insertedId).isPresent()).isFalse();
    }
}
