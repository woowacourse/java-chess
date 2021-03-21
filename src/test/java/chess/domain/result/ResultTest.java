package chess.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    @DisplayName("Result 생성 테스트")
    @Test
    void getScore() {
        Result result = Result.generateResult(10, 15.3);

        double blackTeamScore = result.getBlackTeamScore();
        double whiteTeamScore = result.getWhiteTeamScore();

        assertThat(blackTeamScore).isEqualTo(10);
        assertThat(whiteTeamScore).isEqualTo(15.3);
    }
}
