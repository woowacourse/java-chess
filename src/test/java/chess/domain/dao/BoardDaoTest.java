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
import chess.domain.Position;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;

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
    @DisplayName("위치에 따라 알맞은 Piece 정보가 반환되는지 확인")
    public void findByPosition() throws Exception {
        Rook testPiece = new Rook(new Position("a1"), Team.WHITE);
        Piece piece = boardDao.findByPosition("a1");
        assertEquals(testPiece, piece);
    }

    @Test
    @DisplayName("예전 position 값으로 Piece를 찾아 제대로 새로운 위치값으로 업데이트해주는지 확인")
    public void editUser() throws Exception {
        boardDao.editPiece("a2", "a4");
        Pawn pawn = new Pawn(new Position("a4"), Team.WHITE);
        assertEquals(boardDao.findByPosition("a4"), pawn);
    }

    @Test
    @DisplayName("save 메서드와 find 메서드가 제대로 동작하는지 확인")
    void save() throws SQLException {
        Board board = new Board();
        boardDao.save(board);
        assertEquals(boardDao.find(), new Board());
    }
}