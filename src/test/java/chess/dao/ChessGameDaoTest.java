package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoard;
import chess.domain.ChessGame;
import chess.domain.InitialPiece;
import chess.domain.TeamColor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class ChessGameDaoTest {

    private static ChessGameDao chessGameDao;
    private long savedId;

    @BeforeEach
    void setUp() {
        chessGameDao = new ChessGameDao(
            new JdbcTemplate(RollbackConnectionProvider.getConnection()));
        savedId = chessGameDao.save(
            new ChessGame(new ChessBoard(InitialPiece.getPiecesWithPosition())));
    }

    @AfterEach
    void clearUp() {
        chessGameDao.rollback();
    }

    @DisplayName("체스 게임 정보가 새로 저장되면, 실행 가능한 체스 게임 목록에 추가된다.")
    @Test
    void 체스_새로_저장() {
        assertThat(chessGameDao.findAllGameId()).contains(savedId);
    }

    @DisplayName("체스 게임이 종료되면 조회되지 않는다.")
    @Test
    void 게임_종료_미조회() {
        chessGameDao.updateStatus(savedId, true);
        assertThat(chessGameDao.findAllGameId()).doesNotContain(savedId);
    }

    @DisplayName("체스 게임의 턴이 바뀌면 반영된다.")
    @Test
    void 턴_변경_반영() {
        chessGameDao.updateTurn(savedId, TeamColor.BLACK);
        assertThat(chessGameDao.findById(savedId).get().getTeamColor()).isEqualTo(TeamColor.BLACK);
    }

}