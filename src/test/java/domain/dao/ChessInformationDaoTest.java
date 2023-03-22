package domain.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import org.junit.jupiter.api.Test;

class ChessInformationDaoTest {

    private final ChessInformationDao chessInformationDao = new ChessInformationDao();

    @Test
    public void connection() {
        final Connection connection = chessInformationDao.getConnection();
        assertThat(connection).isNotNull();
    }
}
