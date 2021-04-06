package chess.domain.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ChessBoardDaoTest {
    private ChessBoardDao chessBoardDAO;

    @BeforeEach
    public void setup() {
        chessBoardDAO = new ChessBoardDao();
    }

    @Test
    @DisplayName("DB 연결 테스트")
    public void connection() {
        Connection con = chessBoardDAO.getConnection();
        assertNotNull(con);
    }

    @Test
    void closeConnection() {
    }

    @Test
    @DisplayName("위치 1개 추가")
    void addPosition() throws SQLException {
        ChessBoardVo chessBoardVO = new ChessBoardVo("a8", "WHITE", "ROOK", "alive");
        System.out.println(chessBoardVO.getPosition());
        chessBoardDAO.addPosition(chessBoardVO);
    }

    @Test
    @DisplayName("보드 추가")
    void addPositions() throws SQLException {
        List<ChessBoardVo> board = new ArrayList<>();
        board.add(new ChessBoardVo("a1", "WHITE", "ROOK", "true"));
        board.add(new ChessBoardVo("a2", "WHITE", "KNIGHT", "true"));
        board.add(new ChessBoardVo("d2", "BLACK", "KING", "true"));
        chessBoardDAO.addPositions(board);
    }

    @Test
    @DisplayName("게임아이디로 모든 보드판 불러오기")
    void findByGameId() throws SQLException {
        List<ChessBoardVo> results = chessBoardDAO.findByGameId("1");
        assertThat(results).isEmpty();
        assertThat(results).contains(
                new ChessBoardVo("a1", "WHITE", "ROOK", "true"),
                new ChessBoardVo("a2", "WHITE", "KNIGHT", "true"),
                new ChessBoardVo("d2", "BLACK", "KING", "true")
        );
    }

    @Test
    @DisplayName("게임아이디가 비어있을 때, 모든 보드판 불러오기")
    void findByGameId_removed() throws SQLException {
        chessBoardDAO.removePositions();
        List<ChessBoardVo> results = chessBoardDAO.findByGameId("1");
        assertThat(results).isEmpty();
    }

    @Test
    @DisplayName("해당 보드의 모든 위치 삭제")
    void removePositions() throws SQLException {
        chessBoardDAO.removePositions();
    }
}