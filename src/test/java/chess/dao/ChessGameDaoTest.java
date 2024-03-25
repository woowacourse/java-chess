package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {
    ChessGameDao chessGameDao;

    @BeforeEach
    void setUp() {
        chessGameDao = new ChessGameDao();
    }

    @DisplayName("데이터베이스 연결이 되었는지 확인한다.")
    @Test
    void getConnection() {
        Connection connection = chessGameDao.getConnection();
        assertThat(connection).isNotNull();
    }
}
