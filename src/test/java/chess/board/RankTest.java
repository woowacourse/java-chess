package chess.board;

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
                .hasMessage("잘못된 행 번호입니다.");
    }
}
