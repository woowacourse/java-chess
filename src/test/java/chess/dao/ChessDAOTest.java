package chess.dao;

import chess.domain.chessgame.ChessGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ChessDAOTest {
    private ChessDAO chessDAO;
//    private static final User USER = new User("testUserId", "testUser");

    @BeforeEach
    public void setup() {
        chessDAO = new ChessDAO();
    }

    @Test
    @DisplayName("db 연결 기능")
    public void connection() {
        Connection con = chessDAO.getConnection();
        assertNotNull(con);
    }

    @Test
    @DisplayName("게임 추가 기능")
    void addGame() throws SQLException {
        chessDAO.addGame("new_game", new ChessGame());
    }
}