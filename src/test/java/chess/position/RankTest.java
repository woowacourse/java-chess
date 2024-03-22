package chess.position;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    @DisplayName("올바르지 않은 행을 변환하는 경우 예외를 발생한다.")
    void invalidRankNumberTest(int rankNumber) {
        Assertions.assertThatThrownBy(() -> Rank.from(rankNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 행 번호입니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 8})
    @DisplayName("범위를 넘어가도록 행을 계산하는 경우 예외를 발생한다.")
    void rankOverflowTest(int difference) {
        Rank rank = Rank.from(1);
        Assertions.assertThatThrownBy(() -> rank.createRankByDifferenceOf(difference))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("행 범위를 벗어납니다.");
    }
}
