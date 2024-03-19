package chess.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PositionTest {

    @DisplayName("x, y 좌표로 Position 객체가 생성된다.")
    @Test
    void createPositionByCoordinateTest() {
        Position position = Position.of("a1");

        Assertions.assertThat(position).isNotNull();
    }

    @DisplayName("보드의 범위를 벗어난 좌표이면 예외를 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a9", "i1", "00", "1a", "aa"})
    void validateRangeTest(String position) {
        Assertions.assertThatThrownBy(() -> Position.of(position))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보드의 범위를 벗어난 좌표입니다.");
    }

    @DisplayName("서로 다른 좌표간의 차이를 반환한다.")
    @Test
    void calculatePositionDifferenceTest() {
        Position position = Position.of("a1");
        Position otherPosition = Position.of("c5");

        PositionDifference actualPositionDifference = position.calculateDifference(otherPosition);
        PositionDifference expectedPositionDifference = new PositionDifference(-2, -4);

        Assertions.assertThat(actualPositionDifference).isEqualTo(expectedPositionDifference);
    }
}
