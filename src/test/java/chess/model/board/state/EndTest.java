package chess.model.board.state;

import static chess.controller.GameCommand.END;
import static chess.controller.GameCommand.MOVE;
import static chess.controller.GameCommand.START;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.dao.MoveDaoImpl;
import chess.dao.MoveTruncator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTest extends MoveTruncator {

    private GameState end;

    @BeforeEach
    void init() {
        final GameState playing = ProgressState.of(START, new MoveDaoImpl());
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
}
