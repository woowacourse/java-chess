package chess.domain.dao;

import chess.service.ChessServiceImpl;
import chess.dao.ChessBoardDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ChessServiceImplTest {
    private ChessServiceImpl chessServiceImpl;

    @BeforeEach
    public void setup() {
        chessServiceImpl = new ChessServiceImpl();
    }

    @Test
    @DisplayName("DB 연결 테스트")
    public void connection() {
        Connection con = chessServiceImpl.getConnection();
        assertNotNull(con);
    }

    @Test
    void closeConnection() {
    }

    @Test
    @DisplayName("위치 1개 추가")
    void addPosition() {
        ChessBoardDao chessBoardDao = new ChessBoardDao("a8", "WHITE", "ROOK", "alive");
        System.out.println(chessBoardDao.getPosition());
        chessServiceImpl.addPosition(chessBoardDao);
    }

    @Test
    @DisplayName("보드 추가")
    void addPositions() {
        List<ChessBoardDao> board = new ArrayList<>();
        board.add(new ChessBoardDao("a1", "WHITE", "ROOK", "true"));
        board.add(new ChessBoardDao("a2", "WHITE", "KNIGHT", "true"));
        board.add(new ChessBoardDao("d2", "BLACK", "KING", "true"));
        chessServiceImpl.addPositions(board);
    }

    @Test
    @DisplayName("게임아이디로 모든 보드판 불러오기")
    void findByGameId() {
        List<ChessBoardDao> results = chessServiceImpl.findByGameId("1");
        assertThat(results).isEmpty();
        assertThat(results).contains(
                new ChessBoardDao("a1", "WHITE", "ROOK", "true"),
                new ChessBoardDao("a2", "WHITE", "KNIGHT", "true"),
                new ChessBoardDao("d2", "BLACK", "KING", "true")
        );
    }

    @Test
    @DisplayName("게임아이디가 비어있을 때, 모든 보드판 불러오기")
    void findByGameId_removed() {
        chessServiceImpl.removePositions();
        List<ChessBoardDao> results = chessServiceImpl.findByGameId("1");
        assertThat(results).isEmpty();
    }

    @Test
    @DisplayName("해당 보드의 모든 위치 삭제")
    void removePositions() {
        chessServiceImpl.removePositions();
    }
}