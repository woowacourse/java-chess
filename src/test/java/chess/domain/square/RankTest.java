package chess.domain.square;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("랭크")
class RankTest {

    @Test
    @DisplayName("숫자 문자를 정상적으로 변환한다.")
    void fromTest() {
        // given
        Rank rank = Rank.from('5');

        // when & then
        assertThat(rank).isEqualTo(Rank.FIVE);
    }

    @ParameterizedTest
    @ValueSource(chars = {'0', '9'})
    @DisplayName("범위 밖의 값일 경우 예외가 발생한다.")
    void validateRangeTest(char input) {
        assertThatCode(() -> Rank.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("범위 밖의 랭크 입니다.");
    }
}
