package dao;

import domain.piece.move.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class DbChessGameDaoTest {

    private final DbChessGameDao dbChessGameDao = new DbChessGameDao();

    @Test
    void getConnection() {
        try (final var connection = dbChessGameDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("좌표를 저장할 수 있다")
    void create() {
        Coordinate start = new Coordinate(1,2);
        Coordinate end = new Coordinate(2, 2);

        assertThatCode(() -> dbChessGameDao.create(start, end))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("좌표를 읽어올 수 있다")
    void read() {
        assertThatCode(dbChessGameDao::read)
                .doesNotThrowAnyException();
    }
}