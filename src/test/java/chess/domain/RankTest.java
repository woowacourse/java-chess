package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RankTest {

    @ParameterizedTest
    @DisplayName("가로줄은 0 ~ 7 값을 벗어날 경우 예외가 발생한다.")
    @ValueSource(ints = {96, 108})
    void validateFileRange(int rank) {
        assertThatThrownBy(() -> new Rank(rank))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("기물의 가로 위치는 최소 0부터 최대 7까지 놓을 수 있습니다.");
    }

}
