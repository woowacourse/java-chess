package chess.db.dao;

import chess.domain.ChessGame;
import chess.db.entity.ChessGameEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class ChessGameDaoTest {

    @Test
    @DisplayName("게임 개수 호출")
    void count() {
        ChessGameDao chessGameDao = new ChessGameDao();

        assertThatCode(chessGameDao::count)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("게임 시작")
    void saveWithRollback() {
        ChessGameDao chessGameDao = new ChessGameDao();

        int chessGameId = chessGameDao.save(new ChessGame());
        chessGameDao.delete(chessGameId);
    }

    @Test
    @DisplayName("게임 호출")
    void findWithRollback() {
        ChessGameDao chessGameDao = new ChessGameDao();
        int chessGameId = chessGameDao.save(new ChessGame());

        ChessGameEntity chessGameEntity = chessGameDao.find(1);
        assertThat(chessGameEntity.getId()).isEqualTo(1);

        chessGameDao.delete(chessGameId);
    }

    @Test
    @DisplayName("피스 이동 시 체스 게임 상태 정보 수정")
    void moveWithRollback() {
        ChessGameDao chessGameDao = new ChessGameDao();
        int chessGameId = chessGameDao.save(new ChessGame());

        assertThatCode(() -> chessGameDao.move(chessGameId, "nextState"))
                .doesNotThrowAnyException();

        chessGameDao.delete(chessGameId);
    }
}
