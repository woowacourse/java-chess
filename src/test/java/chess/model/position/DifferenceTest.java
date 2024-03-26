package chess.model.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DifferenceTest {

    @Test
    @DisplayName("캐싱되어 항상 동일한 객체를 반환한다.")
    void from() {
        // given
        Difference difference = Difference.from(2);
        Difference otherDifference = Difference.from(2);

        // when
        boolean result = difference.equals(otherDifference);

        // then
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {8, -8})
    @DisplayName("좌표차의 절댓값이 7 초과이면 예외가 발생한다.")
    void validateDifference(int difference) {
        // whe & then
        assertThatThrownBy(
                () -> Difference.from(difference)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("절댓값으로 두 좌표차의 합을 구한다.")
    void plusByAbsoluteValue() {
        // given
        Difference target = Difference.from(-1);
        Difference other = Difference.from(4);

        // when
        int sum = target.plusByAbsoluteValue(other);

        // then
        assertThat(sum).isEqualTo(5);
    }
}
