package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PositionTest {

    @Test
    @DisplayName("Position 같은 좌표 비교")
    void equalsSucceed() {
        Position position1 = new Position(Column.A, Row.FIFTH);
        Position position2 = new Position(Column.A, Row.FIFTH);

        assertThat(position1).isEqualTo(position2);
    }

    @ParameterizedTest(name = "Position 다른 좌표 비교")
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

    @ParameterizedTest(name = "세로 이동경로 반환")
    @MethodSource("getRouteVerticalTestcase")
    void getRouteVertical(Position from, Position to) {
        assertThat(Position.route(from, to))
                .contains(Position.from("c1"), Position.from("b1"));
    }

    private static Stream<Arguments> getRouteVerticalTestcase() {
        return Stream.of(
                Arguments.of(Position.from("a1"), Position.from("d1")),
                Arguments.of(Position.from("d1"), Position.from("a1"))

        );
    }

    @ParameterizedTest(name = "가로 이동경로 반환")
    @MethodSource("getRouteHorizontalTestcase")
    void getRouteHorizontal(Position from, Position to) {
        assertThat(Position.route(from, to))
                .contains(Position.from("b2"), Position.from("b3"));
    }

    private static Stream<Arguments> getRouteHorizontalTestcase() {
        return Stream.of(
                Arguments.of(Position.from("b1"), Position.from("b4")),
                Arguments.of(Position.from("b4"), Position.from("b1"))
        );
    }

    @ParameterizedTest(name = "대각선(경사도 1) 이동경로 반환")
    @MethodSource("getRoutePositiveInclineTestcase")
    void getRoutePositiveIncline(Position from, Position to) {
        assertThat(Position.route(from, to))
                .contains(Position.from("c3"), Position.from("d4"));
    }

    private static Stream<Arguments> getRoutePositiveInclineTestcase() {
        return Stream.of(
                Arguments.of(Position.from("b2"), Position.from("e5")),
                Arguments.of(Position.from("e5"), Position.from("b2"))
        );
    }

    @ParameterizedTest(name = "대각선(경사도 -1) 이동경로 반환")
    @MethodSource("getRouteNegativeInclineTestcase")
    void getRouteNegativeIncline(Position from, Position to) {
        assertThat(Position.route(from, to))
                .contains(Position.from("c4"), Position.from("d3"));
    }

    private static Stream<Arguments> getRouteNegativeInclineTestcase() {
        return Stream.of(
                Arguments.of(Position.from("b5"), Position.from("e2")),
                Arguments.of(Position.from("e2"), Position.from("b5"))
        );
    }
}
