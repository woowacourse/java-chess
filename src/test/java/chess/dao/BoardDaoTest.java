package chess.dao;

import chess.domains.board.Board;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BoardDaoTest {
    private static BoardDao boardDao;

    @BeforeAll
    public static void setup() {
        boardDao = new BoardDao();
    }

    @DisplayName("연결 가능 여부 테스트")
    @Test
    public void connection() {
        Connection con = boardDao.getConnection();
        assertNotNull(con);
    }

    @DisplayName("보드 입력 테스트")
    @Test
    public void createBoard() {
        Board board = new Board();

        boardDao.createBoard(board.getBoard());
    }

    @DisplayName("보드 전체 삭제 테스트")
    @Test
    public void deleteBoard() {
        boardDao.deleteBoard();
    }
}