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

        assertThatNoException().isThrownBy(state::calculateStatus);
    }

    @DisplayName("기물이 없는 시작 위치를 입력할 경우 움직일 수 없다.")
    @Test
    void move_null_piece_exception() {
        final State state = new Running(new Board());
        Position a3 = Position.of(Column.A, Row.THREE);
        Position a4 = Position.of(Column.A, Row.FOUR);

        assertThatThrownBy(() -> state.move(a3, a4))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이동할 수 있는 기물이 없습니다.");
    }

    @DisplayName("해당 진영의 턴이 아닌 경우 움직일 수 없다.")
    @Test
    void move_opposite_turn_exception() {
        final State state = new Running(new Board());
        Position a3 = Position.of(Column.A, Row.SEVEN);
        Position a4 = Position.of(Column.A, Row.FIVE);

        assertThatThrownBy(() -> state.move(a3, a4))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("상대 진영의 차례입니다.");
    }

    @DisplayName("경로에 기물이 있을 경우 움직일 수 없다.")
    @Test
    void move_obstacle_exception() {
        final State state = new Running(new Board());
        Position a1 = Position.of(Column.A, Row.ONE);
        Position a4 = Position.of(Column.A, Row.FOUR);

        assertThatThrownBy(() -> state.move(a1, a4))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("경로에 기물이 있어 움직일 수 없습니다.");
    }
}
