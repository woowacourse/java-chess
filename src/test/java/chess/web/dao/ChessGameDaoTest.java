package chess.web.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {

    @DisplayName("커넥션 테스트")
    @Test
    public void connection() {
        //given
        ChessGameDao chessGameDao = new ChessGameDao();

        //when
        Connection connection = chessGameDao.getConnection();

        //then
        assertThat(connection).isNotNull();
    }
}