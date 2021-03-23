package chess.domain.gamestate.running;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.gamestate.CommandType;
import chess.domain.gamestate.State;
import chess.domain.gamestate.finished.End;
import chess.utils.BoardUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    private Board board;
    private State state;

    @BeforeEach
    void setUp() {
        board = BoardUtil.generateInitialBoard();
        state = new Ready(board);
    }

    @DisplayName("상태 변경 - ready 상태에서 start로 변경가능하다.")
    @Test
    void changeState_1() {
        // given
        CommandType start = CommandType.START;

        // when
        state = state.changeCommand(start);

        // then
        assertThat(state).isInstanceOf(Start.class);
    }

    @DisplayName("상태 변경 - ready 상태에서 end로 변경가능하다.")
    @Test
    void changeState_2() {
        // given
        CommandType end = CommandType.END;

        // when
        state = state.changeCommand(end);

        // then
        assertThat(state).isInstanceOf(End.class);
    }

    @DisplayName("상태 변경 - ready 상태에서 move 상태로 변경 불가능하다.")
    @Test
    void changeState_3() {
        // given
        CommandType move = CommandType.MOVE;

        // then
        assertThatThrownBy(() -> state.changeCommand(move))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("상태 변경 - ready 상태에서 status 상태로 변경 불가능하다.")
    @Test
    void changeState_4() {
        // given
        CommandType status = CommandType.STATUS;

        // then
        assertThatThrownBy(() -> state.changeCommand(status))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
