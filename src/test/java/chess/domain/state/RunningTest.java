package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.square.Color;
import chess.domain.square.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunningTest {
    @Test
    @DisplayName("팀의 점수를 계산한다.")
    void calculateScore() {
        // given
        State state = new Running();

        // when
        double score = state.calculateScore(Team.from(Color.WHITE));

        // expected
        assertThat(score).isEqualTo(38.0);
    }
}
