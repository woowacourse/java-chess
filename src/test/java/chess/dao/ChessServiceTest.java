package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.dto.StateType;
import chess.domain.state.ChessGame;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessServiceTest {

    private ChessService chessService;
    private PieceDao pieceDao;
    private TurnDao turnDao;

    @BeforeEach
    void setUp() {
        pieceDao = new MemoryPieceDao();
        turnDao = new MemoryTurnDao();
        chessService = new ChessService(pieceDao, turnDao);
    }

    @DisplayName("ready를 할 경우 게임은 준비 상태가 된다.")
    @Test
    void 레디_게임_준비한다() {
        chessService.ready();

        ChessGame chessGame = chessService.getChessGame();
        StateType stateType = StateType.of(chessGame.getState());

        assertThat(stateType).isEqualTo(StateType.READY);
    }

    @DisplayName("새 게임을 생성한다.")
    @Test
    void 새_게임_생성한다() {
        chessService.create();

        ChessGame chessGame = chessService.getChessGame();
        StateType stateType = StateType.of(chessGame.getState());

        assertThat(stateType).isEqualTo(StateType.WHITE_TURN);
    }

    @DisplayName("체스의 기물을 움직인다.")
    @Test
    void 기물_움직인다() {
        chessService.create();

        ChessGame chessGame = chessService.getChessGame();
        chessGame.move("a2", "a4");
        StateType stateType = StateType.of(chessGame.getState());

        assertThat(stateType).isEqualTo(StateType.BLACK_TURN);
    }

    @AfterEach
    void tearDown() {
        pieceDao.removeAll();
    }
}
