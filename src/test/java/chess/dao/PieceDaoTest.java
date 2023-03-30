package chess.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class PieceDaoTest {

    private final PieceDao pieceDao = new PieceDao();

    @Test
    public void connection() throws SQLException {
        try (final var connection = pieceDao.getConnection()) {
            Assertions.assertThat(connection).isNotNull();
        }
    }

    @Test
    public void deleteTable_test(){
        pieceDao.deleteTable();
        Assertions.assertThat(pieceDao.hasData()).isFalse();
    }

}
