package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DirectionTest {
    @DisplayName("of에 두 Position을 전달하면, Direction을 반환한다.")
    @ParameterizedTest(name = "({0}, {1}), ({2}, {3}) => {4} 방향")
    @CsvSource(value = {
            "A,ONE,A,TWO,VERTICAL_UP",
            "A,ONE,B,TWO,DIAGONAL_RIGHT_UP",
            "A,ONE,B,ONE,HORIZONTAL_RIGHT",
            "A,TWO,B,ONE,DIAGONAL_RIGHT_DOWN",
            "A,TWO,A,ONE,VERTICAL_DOWN",
            "B,TWO,A,ONE,DIAGONAL_LEFT_DOWN",
            "B,ONE,A,ONE,HORIZONTAL_LEFT",
            "B,ONE,A,TWO,DIAGONAL_LEFT_UP",
            "A,ONE,B,THREE,SEVEN_SHAPE",
            "A,ONE,C,TWO,SEVEN_SHAPE",
            "C,ONE,B,THREE,SEVEN_SHAPE",
            "C,ONE,A,TWO,SEVEN_SHAPE",
            "B,THREE,A,ONE,SEVEN_SHAPE",
            "C,TWO,A,ONE,SEVEN_SHAPE",
            "B,THREE,C,ONE,SEVEN_SHAPE",
            "A,TWO,C,ONE,SEVEN_SHAPE",
    })
    void of_returnsDirectionWithTwoPositions(String fromXAxis, String fromYAxis, String toXAxis, String toYAxis,
                                             String expectedDirection) {
        // given
        Position from = Position.of(XAxis.valueOf(fromXAxis), YAxis.valueOf(fromYAxis));
        Position to = Position.of(XAxis.valueOf(toXAxis), YAxis.valueOf(toYAxis));

        // when
        Direction expected = Direction.of(from, to);

        // then
        assertThat(expected).isEqualTo(Direction.valueOf(expectedDirection));
    }

    @DisplayName("of에 정의되지 않은 방향의 두 Position을 전달하면 UNDEFINED_DIRECTION을 반환한다.")
    @ParameterizedTest(name = "({0}, {1}), ({2}, {3})")
    @CsvSource(value = {"A,ONE,D,TWO", "D,ONE,A,TWO"})
    void of_returnsDirectionWithTwoPositions(String fromXAxis, String fromYAxis, String toXAxis, String toYAxis) {
        // given
        Position from = Position.of(XAxis.valueOf(fromXAxis), YAxis.valueOf(fromYAxis));
        Position to = Position.of(XAxis.valueOf(toXAxis), YAxis.valueOf(toYAxis));

        // when
        Direction direction = Direction.of(from, to);

        // then
        assertThat(direction).isEqualTo(Direction.UNDEFINED_DIRECTION);
    }
}
