package chess.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
    @DisplayName("white가 승리했을 때 올바르게 승패를 판단하는지")
    @Test
    void resultWhite() {
        Result result = new Result(10d, 15d);

        assertThat(result.getBlackOutcome()).isEqualTo(Outcome.LOSE.getOutcome());
        assertThat(result.getWhiteOutcome()).isEqualTo(Outcome.WIN.getOutcome());
    }

    @DisplayName("무승부 했을 때 올바르게 승패를 판단하는지")
    @Test
    void resultDraw() {
        Result result = new Result(15d, 15d);

        assertThat(result.getBlackOutcome()).isEqualTo(Outcome.DRAW.getOutcome());
        assertThat(result.getWhiteOutcome()).isEqualTo(Outcome.DRAW.getOutcome());
    }

    @DisplayName("black이 승리했을 때 올바르게 승패를 판단하는지")
    @Test
    void resultBlack() {
        Result result = new Result(15d, 10d);

        assertThat(result.getBlackOutcome()).isEqualTo(Outcome.WIN.getOutcome());
        assertThat(result.getWhiteOutcome()).isEqualTo(Outcome.LOSE.getOutcome());
    }
}
