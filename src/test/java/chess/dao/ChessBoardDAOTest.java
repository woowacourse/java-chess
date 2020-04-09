package chess.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardDAOTest {
    private ChessBoardDAO chessBoardDAO;

    @BeforeEach
    private void setUp() {
        chessBoardDAO = new ChessBoardDAO();
    }

    @Disabled
    @DisplayName("체스 보드 추가 테스트")
    @Test
    void addChessBoardTest() throws Exception {
        chessBoardDAO.addChessBoard();
    }

    @Disabled
    @DisplayName("가장 최근에 추가된 체스 보드 찾기")
    @Test
    void findRecentChessBoardTest() throws Exception {
        chessBoardDAO.addChessBoard();
        ChessBoard chessBoard = chessBoardDAO.findRecentChessBoard();

        Assertions.assertThat(chessBoard).isNotNull();
        Assertions.assertThat(chessBoard).isInstanceOf(ChessBoard.class);
    }

    @Disabled
    @DisplayName("체스 보드 삭제")
    @Test
    void deleteChessBoardTest() throws Exception {
        ChessBoard chessBoard = chessBoardDAO.findRecentChessBoard();
        chessBoardDAO.deleteChessBoard(chessBoard);
    }

    @Disabled
    @DisplayName("체스 보드 아이디로 체스 보드 찾기")
    @Test
    void findByIdTest() throws Exception {
        int chessBoardId = 9;
        ChessBoard expected = this.chessBoardDAO.findById(chessBoardId);

        Assertions.assertThat(expected.getChessBoardId()).isEqualTo(9);
    }
}
