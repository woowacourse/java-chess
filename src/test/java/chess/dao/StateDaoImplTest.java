package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.board.Board;
import chess.model.state.State;
import chess.model.state.running.BlackTurn;
import chess.model.state.running.WhiteTurn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StateDaoImplTest {

    @DisplayName("데이터가 저장되는지 확인한다.")
    @Test()
    void save() {
        StateDao stateDao = new StateDaoImpl(new FakeDataSource());
        stateDao.delete();
        stateDao.save(new WhiteTurn(Board.init()));
        State state = stateDao.find(Board.init());

        assertThat(state.isWhiteTurn()).isTrue();
    }

    @DisplayName("데이터가 삭제되는지 확인한다.")
    @Test()
    void delete() {
        StateDao stateDao = new StateDaoImpl(new FakeDataSource());
        stateDao.delete();
        stateDao.save(new WhiteTurn(Board.init()));
        stateDao.delete();

        assertThatThrownBy(() -> stateDao.find(Board.init()))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("[ERROR] 데이터 조회 실패");
    }

    @DisplayName("데이터가 최신화 되는지 확인한다.")
    @Test()
    void update() {
        StateDao stateDao = new StateDaoImpl(new FakeDataSource());
        stateDao.delete();
        stateDao.save(new WhiteTurn(Board.init()));
        stateDao.update(new WhiteTurn(Board.init()), new BlackTurn(Board.init()));
        State state = stateDao.find(Board.init());

        assertThat(state.isBlackTurn()).isTrue();
    }
}
