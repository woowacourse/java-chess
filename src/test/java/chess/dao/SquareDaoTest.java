package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.position.Position;
import java.sql.Connection;
import java.util.Map;
import org.junit.jupiter.api.Test;

class SquareDaoTest {

    @Test
    void connection() {
        final SquareDao squareDao = new SquareDao();
        final Connection connection = squareDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    void find() {
        final SquareDao squareDao = new SquareDao();
        final Map<String, String> squares = squareDao.find();

        for (String position : squares.keySet()) {
            assertThat(Position.from(position)).isInstanceOf(Position.class);
        }
    }
}
