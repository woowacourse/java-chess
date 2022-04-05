package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import org.junit.jupiter.api.Test;

public class ChessGameDaoTest {

    @Test
    void connection() {
        final ChessGameDao memberDao = new ChessGameDao();
        final Connection connection = memberDao.getConnection();
        assertThat(connection).isNotNull();
    }
}
