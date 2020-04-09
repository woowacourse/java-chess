package chess.domain.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.dao.BoardDao;
import chess.dao.ConnectionManager;
import chess.domain.Board;

class BoardDaoTest {
    private BoardDao boardDao;

    @BeforeEach
    void setup() {
        boardDao = new BoardDao();
    }

    @Test
    @DisplayName("커넥션이 제대로 연결되었는지 확인")
    void connection() {
        Connection con = ConnectionManager.getConnection();
        assertNotNull(con);
    }

    @Test
    @DisplayName("save 메서드와 find 메서드가 제대로 동작하는지 확인")
    void save() throws SQLException {
        Board board = new Board();
        boardDao.save(board);
        assertEquals(boardDao.find(), new Board());
    }
}