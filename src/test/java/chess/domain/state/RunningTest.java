package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Board;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunningTest {
    
    @Test
    @DisplayName("white상태에서 start를 하면 예외가 발생한다.")
    void whiteStartTest() {
        State state = new White(new Board());
        assertThatThrownBy(state::start).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("white상태에서 end를 하면 예외가 발생한다.")
    void WhiteEndTest() {
        State state = new White(new Board());
        assertThat(state.end()).isInstanceOf(End.class);
    }

    @Test
    @DisplayName("White상태에서 getBoard를 실행하면 보드를 가져온다.")
    void WhiteGetBoardTest() {
        State state = new White(new Board());
        assertThat(state.getBoard()).isInstanceOf(Board.class);
    }

    @Test
    @DisplayName("White상태에서 isRunning을 실행하면 true가 반환된다.")
    void WhiteIsRunningTest() {
        State state = new White(new Board());
        assertThat(state.isRunning()).isTrue();
    }

    @Test
    @DisplayName("White상태에서 move를 실행하면 Black 상태 반환된다.")
    void WhiteMoveTest() {
        State state = new White(new Board());
        assertThat(state.move()).isInstanceOf(Black.class);
    }
}