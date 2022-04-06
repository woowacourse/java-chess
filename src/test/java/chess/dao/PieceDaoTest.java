package chess.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceDaoTest {

    private PieceDao pieceDao;

    @BeforeEach
    void set() throws SQLException {
        pieceDao = new PieceDao();
    }

    @DisplayName("기물들이 저장되는지 테스트")
    @Test
    void save() {
        final Connection connection = MysqlConnector.connect();

        assertDoesNotThrow(()
                -> pieceDao.save(1, 2, "BISHOP_BLACK", "white", 1, connection));
    }
}
