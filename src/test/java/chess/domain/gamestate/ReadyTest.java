package chess.domain.gamestate;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;

public class ReadyTest {

    @DisplayName("Ready 상태에서 start 호출시 Running 상태가 된다.")
    @Test
    void ready_start_running() {
        //given
        State state = new Ready();
        //when
        //then
        assertThat(state.start()).isInstanceOf(Running.class);
    }

    @DisplayName("Ready 상태에서 move 명령 호출시 예외 발생")
    @Test
    void ready_move_exception() {
        //given
        State state = new Ready();
        Position a2 = new Position(Column.A, Row.TWO);
        Position a3 = new Position(Column.A, Row.THREE);
        //when
        //then
        assertThatThrownBy(() -> state.move(a2, a3))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 진행중이 아닐때는 기물을 이동할 수 없습니다.");
    }

    @DisplayName("Ready 상태에서 status 명령 호출 시 예외 발생")
    @Test
    void ready_status_exception() {
        //given
        State state = new Ready();
        //when
        //then
        assertThatThrownBy(state::statusOfBlack)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 진행중이 아닐때는 상태를 확인할 수 없습니다.");
    }
}
