package domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class UnitVectorTest {
    @DisplayName("출발지와 목적지의 거리차에 따라 올바른 이동 방향을 반환한다.")
    @MethodSource("testSource")
    @ParameterizedTest
    void findUnitVectorTest(int rowDiff, int colDiff, UnitVector expected) {
        // When
        UnitVector unitVector = UnitVector.of(rowDiff, colDiff);

        // Then
        assertThat(unitVector).isEqualTo(expected);
    }

    private static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(0, 0, UnitVector.INVALID),
                Arguments.of(-2, 0, UnitVector.UP),
                Arguments.of(-7, 7, UnitVector.UP_RIGHT),
                Arguments.of(0, 5, UnitVector.RIGHT),
                Arguments.of(3, 3, UnitVector.DOWN_RIGHT),
                Arguments.of(4, 0, UnitVector.DOWN),
                Arguments.of(6, -6, UnitVector.DOWN_LEFT),
                Arguments.of(0, -7, UnitVector.LEFT),
                Arguments.of(-3, -3, UnitVector.UP_LEFT),
                Arguments.of(2, 3, UnitVector.INVALID),
                Arguments.of(-2, 1, UnitVector.INVALID)
        );
    }
}
