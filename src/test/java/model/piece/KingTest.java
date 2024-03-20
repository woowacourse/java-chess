package model.piece;

import java.util.stream.Stream;
import model.Camp;
import model.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KingTest {

    @DisplayName("이동할 수 없는 경로면 false를 반환한다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void cantMovable(final Position currentPosition, final Position nextPosition) {
        King king = new King(Camp.BLACK);

        Assertions.assertThat(king.canMovable(currentPosition, nextPosition)).isFalse();
    }

    @DisplayName("이동할 수 없는 경로면 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void invalidRoute(final Position currentPosition, final Position nextPosition) {
        King king = new King(Camp.BLACK);

        Assertions.assertThatThrownBy(() -> king.getRoute(currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }


    static Stream<Arguments> cantMovableParameterProvider() {
        return Stream.of(
                Arguments.of(Position.from("d6"), Position.from("b5")),
                Arguments.of(Position.from("f5"), Position.from("a8")),
                Arguments.of(Position.from("d3"), Position.from("h2")),
                Arguments.of(Position.from("h6"), Position.from("g4"))
        );
    }


    @DisplayName("이동할 수 있는 경로면 true를 반환한다.")
    @ParameterizedTest
    @MethodSource("canMovableParameterProvider")
    void canMovable(final Position currentPosition, final Position nextPosition) {
        King king = new King(Camp.BLACK);

        Assertions.assertThat(king.canMovable(currentPosition, nextPosition)).isTrue();
    }

    static Stream<Arguments> canMovableParameterProvider() {
        return Stream.of(
                Arguments.of(Position.from("e8"), Position.from("d8")),
                Arguments.of(Position.from("e8"), Position.from("e7")),
                Arguments.of(Position.from("e8"), Position.from("d7")),
                Arguments.of(Position.from("e8"), Position.from("f7")),
                Arguments.of(Position.from("e8"), Position.from("f8")),
                Arguments.of(Position.from("e1"), Position.from("d2")),
                Arguments.of(Position.from("e1"), Position.from("e2")),
                Arguments.of(Position.from("e1"), Position.from("f2"))
        );
    }
}
