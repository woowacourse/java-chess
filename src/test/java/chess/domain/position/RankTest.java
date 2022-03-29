package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    @DisplayName("문자열에 해당하는 Rank을 찾는다.")
    void of() {
        // given
        final String value = "1";

        // when
        final Rank rank = Rank.of(value);
        final String actual = rank.value();

        // then
        assertThat(actual).isEqualTo(value);
    }

    @Test
    @DisplayName("입력된 value가 유효하지 않은 범위이면 예외를 발생시킨다.")
    void of_exception() {
        // given
        final String value = "9";

        // then
        assertThatThrownBy(() -> Rank.of(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 범위입니다.");
    }
}
