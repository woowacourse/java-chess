package chess.dao;

import static org.assertj.core.api.Assertions.assertThatNoException;

import chess.domain.ChessBoard;
import chess.domain.state.BlackTurn;
import chess.domain.state.GameState;
import chess.domain.state.Ready;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {

    ChessGameDao chessGameDao = new ChessGameDao();

    @Test
    @DisplayName("체스 게임의 상태를 DB에 저장한다.")
    void save() {
        GameState gameState = new Ready();
        assertThatNoException().isThrownBy(() -> chessGameDao.save(gameState));
    }

    @Test
    @DisplayName("체스 보드의 id를 조회한다.")
    void findId() {
        assertThatNoException().isThrownBy(() -> chessGameDao.findId());
    }

    @Test
    @DisplayName("체스 보드의 상태명을 조회한다.")
    void findStateName() {
        assertThatNoException().isThrownBy(() -> chessGameDao.findStateName());
    }

    @Test
    @DisplayName("DB에 저장된 체스 게임의 상태를 업데이트한다.")
    void update() {
        GameState gameState = new BlackTurn(ChessBoard.create());
        assertThatNoException().isThrownBy(() -> chessGameDao.update(gameState));
    }

    @Test
    @DisplayName("체스 게임의 상태를 DB에서 제거한다.")
    void delete() {
        assertThatNoException().isThrownBy(chessGameDao::delete);
    }
}
