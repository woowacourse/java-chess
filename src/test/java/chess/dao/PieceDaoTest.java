package chess.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

public class PieceDaoTest {
    @Test
    void connection() {
        final PieceDao pieceDao = new PieceDao();
        final Connection connection = pieceDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    void insertPieces() {
        final PieceDao pieceDao = new PieceDao();
    }
}
