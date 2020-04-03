package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class chessDAOTest {
    private static ChessDAO chessDAO;

    @Test
    @DisplayName("데이터베이스에 연결하는 것을 테스트")
    public void connection() {
        chessDAO = new ChessDAO();
        Connection con = chessDAO.getConnection();
        assertNotNull(con);
    }
}