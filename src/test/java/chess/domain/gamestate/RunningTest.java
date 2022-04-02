package chess.domain.gamestate;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;

public class RunningTest {

    @DisplayName("Running 상태에서 start 호출시 예외가 발생한다.")
    @Test
    void running_start_exception() {
        //given
        State state = new Running(new Board());
        //then
        assertThatThrownBy(state::start)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("진행 중일 때는 시작할 수 없습니다.");
    }

    @DisplayName("Running 상태에서 end 호출시 Finished 상태가 된다.")
    @Test
    void running_end_finished() {
        //given
        State state = new Running(new Board());
        //then
        assertThat(state.end()).isInstanceOf(Finished.class);
    }

    @DisplayName("Running 상태에서 move 명령 호출할 수 있다.")
    @Test
    void running_move_no_exception() {
        //given
        State state = new Running(new Board());
        Position a2 = Position.of(Column.A, Row.TWO);
        Position a3 = Position.of(Column.A, Row.THREE);
        //then
        assertThatNoException().isThrownBy(() -> state.move(a2, a3));
    }

    @DisplayName("Running 상태에서 status 명령 호출할 수 있다.")
    @Test
    void running_status_no_exception() {
        //given
        State state = new Running(new Board());
        //then
        assertThatNoException().isThrownBy(state::statusOfBlack);
    }
}
