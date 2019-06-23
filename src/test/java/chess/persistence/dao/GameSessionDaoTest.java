package chess.persistence.dao;

import chess.domain.GameResult;
import chess.persistence.DataSourceFactory;
import chess.persistence.dto.GameSessionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class GameSessionDaoTest {
    GameSessionDao dao;

    @BeforeEach
    void init() {
        dao = new GameSessionDao(new DataSourceFactory().createDataSource());
    }

    @Test
    void insertAndFind() throws SQLException {
        GameSessionDto room = new GameSessionDto();
        room.setTitle("some room");
        room.setState(GameResult.KEEP.name());
        long insertedId = dao.addSession(room);
        GameSessionDto found = dao.findById(insertedId).get();
        assertThat(found.getTitle()).isEqualTo(room.getTitle());
        assertThat(found.getState()).isEqualTo(room.getState());
        dao.deleteById(insertedId);
    }

    @Test
    void findByTitle() throws SQLException {
        GameSessionDto room = new GameSessionDto();
        room.setTitle("some other room");
        long insertedId = dao.addSession(room);
        Optional<GameSessionDto> maybeFound = dao.findByTitle("some other room");
        assertThat(maybeFound.isPresent()).isTrue();
        dao.deleteById(insertedId);
    }

    @Test
    void deleteById() throws SQLException {
        GameSessionDto room = new GameSessionDto();
        room.setTitle("some otheeeer room");
        long insertedId = dao.addSession(room);
        int affected = dao.deleteById(insertedId);
        assertThat(affected).isEqualTo(1);
        assertThat(dao.findById(insertedId).isPresent()).isFalse();
    }
}
