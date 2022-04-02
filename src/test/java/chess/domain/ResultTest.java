package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ResultTest {

    @DisplayName("각 진영의 점수에 따른 체스 게임의 결과를 반환한다.")
    @ParameterizedTest
    @CsvSource({"21,WHITE", "20,DRAW", "19,BLACK"})
    void 각진영의_점수에따른_체스게임의_결과를_반환한다(double whiteScore, Result result) {
        assertThat(Result.of(whiteScore, 20)).isEqualTo(result);
    }
}
