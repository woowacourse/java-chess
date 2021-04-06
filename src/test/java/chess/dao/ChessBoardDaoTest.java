package chess.dao;

import chess.dto.ChessCellDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ChessBoardDaoTest {
    private ChessBoardDao chessBoardDao;

    @BeforeEach
    public void setup() {
        chessBoardDao = new ChessBoardDao();
    }

    @Test
    @DisplayName("DB 연결 테스트")
    public void connection() {
        Connection con = chessBoardDao.getConnection();
        assertNotNull(con);
    }

    @Test
    void closeConnection() {
    }

    @Test
    @DisplayName("위치 1개 추가")
    void addPosition() {
        ChessCellDto chessCellDto = new ChessCellDto("a8", "WHITE", "ROOK", true);
        System.out.println(chessCellDto.getPosition());
        chessBoardDao.addPosition(chessCellDto);
    }

    @Test
    @DisplayName("보드 추가")
    void addPositions() {
        List<ChessCellDto> board = new ArrayList<>();
        board.add(new ChessCellDto("a1", "WHITE", "ROOK", true));
        board.add(new ChessCellDto("a2", "WHITE", "KNIGHT", true));
        board.add(new ChessCellDto("d2", "BLACK", "KING", true));
        chessBoardDao.addPositions(board);
    }

    @Test
    @DisplayName("게임아이디로 모든 보드판 불러오기")
    void findByGameId() {
        List<ChessCellDto> results = chessBoardDao.findPositions();
        assertThat(results).contains(
                new ChessCellDto("a1", "WHITE", "ROOK", true),
                new ChessCellDto("a2", "WHITE", "KNIGHT", true),
                new ChessCellDto("d2", "BLACK", "KING", true)
        );
    }

    @Test
    @DisplayName("게임아이디가 비어있을 때, 모든 보드판 불러오기")
    void findByGameId_removed() {
        chessBoardDao.removePositions();
        List<ChessCellDto> results = chessBoardDao.findPositions();
        assertThat(results).isEmpty();
    }

    @Test
    @DisplayName("해당 보드의 모든 위치 삭제")
    void removePositions() {
        chessBoardDao.removePositions();
    }
}