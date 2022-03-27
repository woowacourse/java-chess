package chess.domain.gamestate;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FinishedTest {

    @DisplayName("Finished 상태에서 start 호출시 Running 상태가 된다.")
    @Test
    void finished_start_running() {
        State state = new Finished(new Board());

        assertThat(state.start()).isInstanceOf(Running.class);
    }
}
