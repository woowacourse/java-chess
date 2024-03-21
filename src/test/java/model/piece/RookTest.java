package model.piece;

import java.util.stream.Stream;
import model.Camp;
import model.position.Moving;
import model.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RookTest {

    @DisplayName("이동할 수 없는 경로면 false를 반환한다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void cantMovable(Moving moving) {
        Rook rook = new Rook(Camp.BLACK);

        Assertions.assertThat(rook.canMovable(moving)).isFalse();
    }

    @DisplayName("이동할 수 없는 경로면 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void invalidRoute(Moving moving) {
        Rook rook = new Rook(Camp.BLACK);

        Assertions.assertThatThrownBy(() -> rook.getRoute(moving))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> cantMovableParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(Position.from("a8"), Position.from("b7"))),
                Arguments.of(new Moving(Position.from("a8"), Position.from("c4"))),
                Arguments.of(new Moving(Position.from("b3"), Position.from("f7"))),
                Arguments.of(new Moving(Position.from("e3"), Position.from("h5")))
        );
    }

    @DisplayName("이동할 수 있는 경로면 true를 반환한다.")
    @ParameterizedTest
    @MethodSource("canMovableParameterProvider")
    void canMovable(Moving moving) {
        Rook rook = new Rook(Camp.BLACK);

        Assertions.assertThat(rook.canMovable(moving)).isTrue();
    }

    static Stream<Arguments> canMovableParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(Position.from("a8"), Position.from("a2"))),
                Arguments.of(new Moving(Position.from("h8"), Position.from("d8"))),
                Arguments.of(new Moving(Position.from("c3"), Position.from("c1"))),
                Arguments.of(new Moving(Position.from("g2"), Position.from("g6")))
        );
    }
}
