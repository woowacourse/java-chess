package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    @DisplayName("Position 같은 좌표 비교")
    void equalsSucceed() {
        Position position1 = new Position(Column.A, Row.FIFTH);
        Position position2 = new Position(Column.A, Row.FIFTH);

        assertThat(position1).isEqualTo(position2);
    }

    @ParameterizedTest
    @DisplayName("Position 다른 좌표 비교")
    @MethodSource("equalsFailTestcase")
    void equalsFail(Position position1, Position position2) {
        assertThat(position1).isNotEqualTo(position2);
    }

    private static Stream<Arguments> equalsFailTestcase() {
        return Stream.of(
                Arguments.of(new Position(Column.A, Row.FIRST), new Position(Column.B, Row.FIRST)),
                Arguments.of(new Position(Column.A, Row.FIRST), new Position(Column.A, Row.SECOND))
        );
    }

    @ParameterizedTest
    @MethodSource("getRouteVerticalTestcase")
    void getRouteVertical(Position from, Position to) {
        assertThat(Position.getRoute(from, to))
                .contains(Position.of("c1"), Position.of("b1"));
    }

    private static Stream<Arguments> getRouteVerticalTestcase() {
        return Stream.of(
                Arguments.of(Position.of("a1"), Position.of("d1")),
                Arguments.of(Position.of("d1"), Position.of("a1"))

        );
    }

    @ParameterizedTest
    @MethodSource("getRouteHorizontalTestcase")
    void getRouteHorizontal(Position from, Position to) {
        assertThat(Position.getRoute(from, to))
                .contains(Position.of("b2"), Position.of("b3"));
    }

    private static Stream<Arguments> getRouteHorizontalTestcase() {
        return Stream.of(
                Arguments.of(Position.of("b1"), Position.of("b4")),
                Arguments.of(Position.of("b4"), Position.of("b1"))
        );
    }

    @ParameterizedTest
    @MethodSource("getRoutePositiveInclineTestcase")
    void getRoutePositiveIncline(Position from, Position to) {
        assertThat(Position.getRoute(from, to))
                .contains(Position.of("c3"), Position.of("d4"));
    }

    private static Stream<Arguments> getRoutePositiveInclineTestcase() {
        return Stream.of(
                Arguments.of(Position.of("b2"), Position.of("e5")),
                Arguments.of(Position.of("e5"), Position.of("b2"))
        );
    }

    @ParameterizedTest
    @MethodSource("getRouteNegativeInclineTestcase")
    void getRouteNegativeIncline(Position from, Position to) {
        assertThat(Position.getRoute(from, to))
                .contains(Position.of("c4"), Position.of("d3"));
    }

    private static Stream<Arguments> getRouteNegativeInclineTestcase() {
        return Stream.of(
                Arguments.of(Position.of("b5"), Position.of("e2")),
                Arguments.of(Position.of("e2"), Position.of("b5"))
        );
    }
}
