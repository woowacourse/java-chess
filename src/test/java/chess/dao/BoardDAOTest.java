package chess.dao;

import chess.domains.piece.Piece;
import chess.domains.piece.PieceColor;
import chess.domains.piece.Rook;
import chess.domains.position.Position;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BoardDAOTest {
    private static BoardDAO boardDao;

    @BeforeAll
    public static void setup() {
        boardDao = new BoardDAO();
    }

    @DisplayName("연결 가능 여부 테스트")
    @Test
    public void connection() {
        Connection con = boardDao.getConnection();
        assertNotNull(con);
    }

    @DisplayName("보드 전체 삭제 테스트")
    @Test
    public void deleteBoard() throws Exception {
        boardDao.deleteBoard();
    }

    @DisplayName("Piece 추가 테스트")
    @Test
    public void addPiece() throws Exception {
        Position position = Position.ofPositionName("a8");
        Piece piece = new Rook(PieceColor.BLACK);
        boardDao.addPiece(position, piece);
    }
}