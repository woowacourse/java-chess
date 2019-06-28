package chess.dao;

import chess.domain.board.BoardInputForTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameDaoTest {
    private static final int NEW_GAME_ID = 1;
    private static final int WHITE_TURN = 1;
    private static final int BLACK_TURN = -1;

    private ChessGameDao chessGameDao;

    @BeforeEach
    public void setUp() {
        chessGameDao = ChessGameDao.getInstance();
    }

    @Test
    public void 게임의_보드_상태를_제대로_불러오는지_테스트() {
        assertThat(chessGameDao.findChessGameById(NEW_GAME_ID)).isEqualTo(BoardInputForTest.DEFAULT_BOARD);
    }

    @Test
    public void 게임의_차례를_제대로_불러오는지_테스트() {
        assertThat(chessGameDao.findTurnById(NEW_GAME_ID)).isEqualTo(BLACK_TURN);
    }

    @Test
    public void 최근_게임_id를_제대로_불러오는지_테스트() {
        int latestId = chessGameDao.addChessGame(BoardInputForTest.CHECKMATE_BOARD, WHITE_TURN);
        assertThat(chessGameDao.findLatestChessGameId()).isEqualTo(latestId);
        게임을_제대로_삭제하는지_테스트();
    }

    @Test
    public void 게임_진행상태를_제대로_저장하는지_테스트() {
        chessGameDao.addChessGame(BoardInputForTest.CHECKMATE_BOARD, WHITE_TURN);
        게임을_제대로_삭제하는지_테스트();
    }

    @Test
    public void 게임을_제대로_삭제하는지_테스트() {
        chessGameDao.removeChessGameById(chessGameDao.findLatestChessGameId());
    }

    @AfterEach
    public void tearDown() {
        chessGameDao = null;
    }
}