package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RankTest {
    @DisplayName("Rank 범위 검증 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"0", "9", "a"})
    void whenFileOutOfBound(String testString) {
        assertThatThrownBy(() -> Rank.from(testString))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당하는 문자의 Rank가 없습니다.");
    }
}