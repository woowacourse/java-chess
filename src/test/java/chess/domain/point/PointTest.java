package chess.domain.point;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class PointTest {
    @DisplayName("생성한다.")
    @Test
    void create() {
        // expect
        assertThatNoException().isThrownBy(() -> Point.create(0.0));
    }

    @DisplayName("더한다")
    @ParameterizedTest
    @MethodSource("plusDummy")
    void plus(final double valueOne, final double valueTwo, final Point expected) {
        // given
        final Point pointOne = Point.create(valueOne);
        final Point pointTwo = Point.create(valueTwo);

        // when
        final Point plusPoint = pointOne.plus(pointTwo);

        // then
        assertThat(plusPoint).isEqualTo(expected);
    }

    static Stream<Arguments> plusDummy() {
        return Stream.of(
                Arguments.arguments(0.0, 1.0, Point.create(1.0)),
                Arguments.arguments(1.0, 1.0, Point.create(2.0)),
                Arguments.arguments(1.5, 1.0, Point.create(2.5)),
                Arguments.arguments(1.5, 1.5, Point.create(3.0)),
                Arguments.arguments(10.5, 10.5, Point.create(21))
        );
    }

    @DisplayName("뺀다.")
    @ParameterizedTest
    @MethodSource("minusDummy")
    void minus(final double valueOne, final double valueTwo, final Point expected) {
        // given
        final Point pointOne = Point.create(valueOne);
        final Point pointTwo = Point.create(valueTwo);

        // when
        final Point minusPoint = pointOne.minus(pointTwo);

        // then
        assertThat(minusPoint).isEqualTo(expected);
    }

    static Stream<Arguments> minusDummy() {
        return Stream.of(
                Arguments.arguments(0.0, 1.0, Point.create(-1.0)),
                Arguments.arguments(1.0, 1.0, Point.create(0.0)),
                Arguments.arguments(1.5, 1.0, Point.create(0.5)),
                Arguments.arguments(1.5, 1.5, Point.create(0.0)),
                Arguments.arguments(10.5, 10.5, Point.create(0.0))
        );
    }

    @DisplayName("곱하다.")
    @ParameterizedTest
    @MethodSource("timesDummy")
    void times(final double valueOne, final int valueTwo, final Point expected) {
        // given
        final Point pointOne = Point.create(valueOne);

        // when
        final Point timesPoint = pointOne.times(valueTwo);

        // then
        assertThat(timesPoint).isEqualTo(expected);
    }

    static Stream<Arguments> timesDummy() {
        return Stream.of(
                Arguments.arguments(0.0, 1, Point.create(0.0)),
                Arguments.arguments(1.0, 1, Point.create(1.0)),
                Arguments.arguments(1.5, 1, Point.create(1.5)),
                Arguments.arguments(1.5, 2, Point.create(3.0)),
                Arguments.arguments(10.5, 10, Point.create(105.0))
        );
    }

    @DisplayName("더 큰지 비교한다.")
    @ParameterizedTest
    @MethodSource("isGreaterThanDummy")
    void isGreaterThan(final Point left, final Point right, final boolean expected) {
        // given & when
        final boolean isGreaterThan = left.isGreaterThan(right);

        // then
        assertThat(isGreaterThan).isEqualTo(expected);
    }

    static Stream<Arguments> isGreaterThanDummy() {
        return Stream.of(
                Arguments.arguments(Point.ZERO, Point.create(1.0), false),
                Arguments.arguments(Point.ZERO, Point.create(2.0), false),
                Arguments.arguments(Point.ZERO, Point.create(3.0), false),
                Arguments.arguments(Point.ZERO, Point.create(4.0), false),
                Arguments.arguments(Point.create(10.0), Point.ZERO, true),
                Arguments.arguments(Point.create(10.0), Point.create(1.0), true),
                Arguments.arguments(Point.create(10.0), Point.create(2.0), true),
                Arguments.arguments(Point.create(10.0), Point.create(3.0), true),
                Arguments.arguments(Point.create(10.0), Point.create(4.0), true)
        );
    }
}
