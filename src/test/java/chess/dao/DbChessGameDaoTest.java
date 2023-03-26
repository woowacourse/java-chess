package chess.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DbChessGameDaoTest {

    private final ChessGameDao chessGameDao = new DbChessGameDao();
    private long chessGameId;

    @BeforeEach
    void setUp() {
        chessGameId = chessGameDao.create();
    }

    @Test
    @DisplayName("체스게임을 생성한다.")
    void create() {
        assertDoesNotThrow(chessGameDao::create);
    }

    @Test
    @DisplayName("체스게임의 아이디로 게임을 찾는다.")
    void findById() {
        assertDoesNotThrow(
                () -> chessGameDao.findById(chessGameId)
        );
    }

    @Test
    @DisplayName("턴을 변경한다.")
    void updateTurn() {
        assertDoesNotThrow(
                () -> chessGameDao.updateTurn(chessGameDao.findById(chessGameId))
        );
    }

    @Test
    @DisplayName("저장된 체스게임의 목록을 반환한다.")
    void findAll() {
        assertDoesNotThrow(chessGameDao::findAll);
    }
}
