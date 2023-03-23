package chess.model.board.state;

import static chess.controller.GameCommand.END;
import static chess.controller.GameCommand.MOVE;
import static chess.controller.GameCommand.START;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTest {

    @Test
    @DisplayName("State가 End일 때, execute가 실행되면 오류가 발생한다.")
    void changeState_whenCall_thenFail() {
        // given
        final GameState playing = Start.from(START);
        final GameState end = playing.changeState(END);

        // when, then
        assertThatThrownBy(() -> end.changeState(MOVE))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("State가 End일 때, calculateScores가 실행되면 오류가 발생한다.")
    void calculateScores_whenCall_thenFail() {
        // given
        final GameState playing = Start.from(START);
        final GameState end = playing.changeState(END);

        // when, then
        assertThatThrownBy(end::calculateScores)
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("State가 End일 때, calculateScores가 실행되면 오류가 발생한다.")
    void getBoard_whenCall_thenFail() {
        // given
        final GameState playing = Start.from(START);
        final GameState end = playing.changeState(END);

        // when, then
        assertThatThrownBy(end::getBoard)
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
