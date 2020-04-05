package chess.controller.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ChessBoardDAOTest {
    private ChessBoardDAO chessBoardDAO;

    @BeforeEach
    private void setUp() {
        chessBoardDAO = new ChessBoardDAO();
    }

    @DisplayName("데이터베이스 연결 테스트")
    @Test
    void connectionTest() {
        Connection con = chessBoardDAO.getConnection();
        Assertions.assertThat(con).isNotNull();
    }

    @DisplayName("체스 보드 추가 테스트")
    @Test
    void addChessBoardTest() throws Exception {
        chessBoardDAO.addChessBoard();
    }

    @DisplayName("가장 최근에 추가된 체스 보드 찾기")
    @Test
    void findRecentChessBoardTest() throws Exception {
        ChessBoard chessBoard = chessBoardDAO.findRecentChessBoard();

        Assertions.assertThat(chessBoard).isNotNull();
        Assertions.assertThat(chessBoard).isInstanceOf(ChessBoard.class);
    }
}
