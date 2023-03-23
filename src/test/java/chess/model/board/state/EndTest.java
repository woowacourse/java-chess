package chess.model.board.state;

import static chess.controller.GameCommand.END;
import static chess.controller.GameCommand.START;
import static chess.model.board.PositionFixture.A2;
import static chess.model.board.PositionFixture.A3;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTest {

    @Test
    @DisplayName("State가 End일 때, execute가 실행되면 오류가 발생한다.")
    void givenStart_thenFail() {
        // given
        final GameState playing = Start.from(START);
        final GameState end = playing.execute(END, A2, A3);

        // when, then
        assertThatThrownBy(() -> end.execute(START, A2, A3))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
