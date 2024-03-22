package chess.model.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DifferenceTest {

    @ParameterizedTest
    @ValueSource(ints = {8, 9})
    @DisplayName("좌표차의 절댓값이 7 초과이면 예외가 발생한다.")
    void validateDifference(int difference) {
        // whe & then
        assertThatThrownBy(
                () -> Difference.from(difference)
        ).isInstanceOf(IllegalArgumentException.class);
    }

}
