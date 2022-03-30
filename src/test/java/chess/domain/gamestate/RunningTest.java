package chess.domain.gamestate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.BoardInitializer;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RunningTest {
    private State state;

    @BeforeEach
    void initializeStateAsRunning() {
        this.state = new Running(BoardInitializer.get());
    }

    @DisplayName("Running 상태에서 start 호출시 예외가 발생한다.")
    @Test
    void running_start_exception() {
        assertThatThrownBy(state::start)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("이미 시작되었습니다.");
    }

    @DisplayName("Running 상태에서 end 호출시 Finished 상태가 된다.")
    @Test
    void running_end_finished() {
        assertThat(state.end()).isInstanceOf(Finished.class);
    }

    @DisplayName("Running 상태에서 move 명령 호출할 수 있다.")
    @Test
    void running_move_no_exception() {
        Position a2 = Position.of(Column.A, Row.TWO);
        Position a3 = Position.of(Column.A, Row.THREE);

        assertThatNoException().isThrownBy(() -> state.move(a2, a3));
    }

    @DisplayName("Running 상태에서 status 명령 호출할 수 있다.")
    @Test
    void running_status_no_exception() {
        assertThatNoException().isThrownBy(state::getScores);
    }
}
