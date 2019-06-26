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
        dao = new GameSessionDao(DataSourceFactory.getInstance().createDataSource());
    }

    @Test
    void insertAndFind() throws SQLException {
        GameSessionDto sess = GameSessionDto.of(0, GameResult.KEEP.name(), "some sess");
        sess.setId(dao.addSession(sess));
        GameSessionDto found = dao.findById(sess.getId()).get();
        assertThat(found.getTitle()).isEqualTo(sess.getTitle());
        assertThat(found.getState()).isEqualTo(sess.getState());
        dao.deleteById(sess.getId());
    }

    @Test
    void findByTitle() throws SQLException {
        GameSessionDto sess = GameSessionDto.of(0, GameResult.KEEP.name(), "some other sess");
        sess.setId(dao.addSession(sess));
        Optional<GameSessionDto> maybeFound = dao.findByTitle("some other sess");
        assertThat(maybeFound.isPresent()).isTrue();
        dao.deleteById(sess.getId());
    }

    @Test
    void deleteById() throws SQLException {
        GameSessionDto sess = new GameSessionDto();
        sess.setTitle("some otheeeer sess");
        sess.setState(GameResult.KEEP.name());
        long insertedId = dao.addSession(sess);
        int affected = dao.deleteById(insertedId);
        assertThat(affected).isEqualTo(1);
        assertThat(dao.findById(insertedId).isPresent()).isFalse();
    }

    @Test
    void updateState() throws SQLException {
        GameSessionDto sess = new GameSessionDto();
        sess.setTitle("choboman");
        sess.setState(GameResult.KEEP.name());
        sess.setId(dao.addSession(sess));
        sess.setState(GameResult.BLACK_WIN.name());
        dao.updateSession(sess);
        assertThat(dao.findById(sess.getId()).get().getState()).isEqualTo(GameResult.BLACK_WIN.name());
        dao.deleteById(sess.getId());
    }
}
