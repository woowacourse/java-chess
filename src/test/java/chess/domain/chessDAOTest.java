package chess.domain;

import chess.dao.ChessDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class chessDAOTest {
    @Test
    @DisplayName("데이터베이스에 연결하는 것을 테스트")
    public void connection() {
        ChessDAO chessDAO = ChessDAO.getInstance();
        Connection con = chessDAO.getConnection();
        assertNotNull(con);
    }
}