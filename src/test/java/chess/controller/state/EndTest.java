package chess.controller.state;

import static chess.controller.GameCommand.END;
import static chess.controller.GameCommand.MOVE;
import static chess.controller.GameCommand.START;
import static chess.service.ChessServiceFixture.createService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTest {

    private GameState end;

    @BeforeEach
    void init() {
        final GameState playing = ProgressState.of(START, createService());
        end = playing.changeState(END);
    }

    @Test
    @DisplayName("State가 End일 때, executeAndSave가 실행되면 오류가 발생한다.")
    void changeState_whenCall_thenFail() {
        // when, then
        assertThatThrownBy(() -> end.changeState(MOVE))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("State가 End일 때, calculateScores가 실행되면 오류가 발생한다.")
    void calculateScores_whenCall_thenFail() {
        // when, then
        assertThatThrownBy(end::calculateScores)
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("State가 End일 때, calculateScores가 실행되면 오류가 발생한다.")
    void getBoard_whenCall_thenFail() {
        // when, then
        assertThatThrownBy(end::getBoard)
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("State가 End일 때, isKingDie()가 실행되면 자기자신를 반환한다.")
    void isKingDie_whenCall_thenFail() {
        // when, then
        assertThat(end.isKingDie()).isEqualTo(end);
    }
}
