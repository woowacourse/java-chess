package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PositionVectorTest {

    static Stream<Arguments> parametersProvider1() {
        return Stream.of(
            arguments(DirectionVector.TOP, 5, Vector.of(0, 5)),
            arguments(DirectionVector.TOP_RIGHT, 2, Vector.of(2, 2)),
            arguments(DirectionVector.RIGHT, 2, Vector.of(2, 0)),
            arguments(DirectionVector.BOTTOM_RIGHT, 2, Vector.of(2, -2)),
            arguments(DirectionVector.BOTTOM, 2, Vector.of(0, -2)),
            arguments(DirectionVector.BOTTOM_LEFT, 6, Vector.of(-6, -6)),
            arguments(DirectionVector.LEFT, 5, Vector.of(-5, 0)),
            arguments(DirectionVector.TOP_LEFT, 2, Vector.of(-2, 2))
        );
    }

    static Stream<Arguments> parametersProvider2() {
        return Stream.of(
            arguments(DirectionVector.TOP, Vector.of(0, 5)),
            arguments(DirectionVector.TOP_RIGHT, Vector.of(2, 2)),
            arguments(DirectionVector.RIGHT, Vector.of(2, 0)),
            arguments(DirectionVector.BOTTOM_RIGHT, Vector.of(2, -2)),
            arguments(DirectionVector.BOTTOM, Vector.of(0, -2)),
            arguments(DirectionVector.BOTTOM_LEFT, Vector.of(-6, -6)),
            arguments(DirectionVector.LEFT, Vector.of(-5, 0)),
            arguments(DirectionVector.TOP_LEFT, Vector.of(-2, 2))
        );
    }

    @DisplayName("방향 벡터에 int 값을 넣으면 길이를 가진 벡터로 반환한다.")
    @ParameterizedTest(name = "{index} : {0} * {1} == {2}")
    @MethodSource("parametersProvider1")
    void calculateVector(DirectionVector directionVector, int length, Vector expected) {
        Vector actual = directionVector.multiply(length);
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("방향 벡터와 벡터가 같은 방향인지 판단한다.")
    @ParameterizedTest(name = "{index} : {0} == {1}")
    @MethodSource("parametersProvider2")
    void isSameDirection(DirectionVector directionVector, Vector vector) {
        boolean actual = directionVector.isSameDirection(vector);
        assertThat(actual).isTrue();
    }
}
