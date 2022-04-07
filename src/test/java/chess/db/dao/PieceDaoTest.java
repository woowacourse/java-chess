package chess.db.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

class PieceDaoTest {

    @Test
    @DisplayName("연결 확인")
    void connection() {
        ChessGameDao chessGameDao = new ChessGameDao();
        Connection connection = chessGameDao.getConnection();

        assertThat(connection).isNotNull();
    }
}
