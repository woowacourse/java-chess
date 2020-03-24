import chess.domain.Coordinate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CoordinateTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
    void create(int value) {
        assertThat(Coordinate.of(value)).isEqualTo(Coordinate.of(value));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 9, -1})
    void rangeTest(int input) {
        assertThatThrownBy(() -> Coordinate.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("좌표는 1부터 8 사이의 값만 가능합니다.");
    }
}
