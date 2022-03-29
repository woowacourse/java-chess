package chess.domain.gamestate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RunningTest {

    @DisplayName("Running 상태에서 start 호출시 예외가 발생한다.")
    @Test
    void running_start_exception() {
        State state = new Running(new Board());

        assertThatThrownBy(state::start)
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("진행 중일 때는 시작할 수 없습니다.");
    }

    @DisplayName("Running 상태에서 end 호출시 Finished 상태가 된다.")
    @Test
    void running_end_finished() {
        State state = new Running(new Board());

        assertThat(state.end()).isInstanceOf(Finished.class);
    }

    @DisplayName("Running 상태에서 move 명령 호출할 수 있다.")
    @Test
    void running_move_no_exception() {
        State state = new Running(new Board());
        Position a2 = Position.of(Column.A, Row.TWO);
        Position a3 = Position.of(Column.A, Row.THREE);

        assertThatNoException().isThrownBy(() -> state.move(a2, a3));
    }

    @DisplayName("Running 상태에서 status 명령 호출할 수 있다.")
    @Test
    void running_status_no_exception() {
        State state = new Running(new Board());

        assertThatNoException().isThrownBy(state::statusOfBlack);
    }
}
