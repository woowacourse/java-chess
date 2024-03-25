package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinStatusTest {

    @DisplayName("White, Black 점수로 승패를 계산한다.")
    @ParameterizedTest
    @CsvSource(value = {"10.5,11,BLACK_WIN", "0.5,0,WHITE_WIN", "1.5,1.5,DRAW"})
    void of(final double whiteValue, final double blackValue, final WinStatus expected) {
        // given
        final Score whiteScore = new Score(whiteValue);
        final Score blackScore = new Score(blackValue);

        // when
        final WinStatus winStatus = WinStatus.of(whiteScore, blackScore);

        // then
        Assertions.assertThat(winStatus).isEqualTo(expected);
    }
}
