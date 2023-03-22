package chess.model.board.state;

import static chess.controller.GameCommand.END;
import static chess.controller.GameCommand.START;
import static chess.model.board.PositionFixture.A2;
import static chess.model.board.PositionFixture.A3;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayingTest {

    @Test
    @DisplayName("GameCommand가 Start이면 End가 반환된다.")
    void givenEnd_thenReturnEnd() {
        // given
        final GameState playing = Start.from(START);

        // when
        final GameState end = playing.execute(END, A2, A3);

        // then
        assertThat(end.getClass()).isEqualTo(End.class);
    }

    @Test
    @DisplayName("GameCommand가 Start이면 예외가 발생한다.")
    void givenStart_thenFail() {
        // given
        final GameState playing = Start.from(START);

        // when, then
        assertThatThrownBy(() -> playing.execute(START, A2, A3))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
