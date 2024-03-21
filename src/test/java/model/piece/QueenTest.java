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

class QueenTest {

    @DisplayName("이동할 수 없는 경로면 false를 반환한다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void cantMovable(Moving moving) {
        Queen queen = new Queen(Camp.BLACK);

        Assertions.assertThat(queen.canMovable(moving)).isFalse();
    }

    @DisplayName("이동할 수 없는 경로면 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void invalidRoute(Moving moving) {
        Queen queen = new Queen(Camp.BLACK);

        Assertions.assertThatThrownBy(() -> queen.getRoute(moving))
                .isInstanceOf(IllegalArgumentException.class);
    }


    static Stream<Arguments> cantMovableParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(Position.from("b3"), Position.from("b3"))),
                Arguments.of(new Moving(Position.from("d8"), Position.from("e5"))),
                Arguments.of(new Moving(Position.from("d6"), Position.from("a2"))),
                Arguments.of(new Moving(Position.from("b5"), Position.from("a8"))),
                Arguments.of(new Moving(Position.from("h1"), Position.from("c5")))
        );
    }


    @DisplayName("이동할 수 있는 경로면 true를 반환한다.")
    @ParameterizedTest
    @MethodSource("canMovableParameterProvider")
    void canMovable(Moving moving) {
        Queen queen = new Queen(Camp.BLACK);

        Assertions.assertThat(queen.canMovable(moving)).isTrue();
    }

    static Stream<Arguments> canMovableParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(Position.from("d8"), Position.from("d1"))),
                Arguments.of(new Moving(Position.from("d8"), Position.from("a8"))),
                Arguments.of(new Moving(Position.from("d8"), Position.from("h8"))),
                Arguments.of(new Moving(Position.from("d8"), Position.from("b6"))),
                Arguments.of(new Moving(Position.from("d8"), Position.from("f6"))),
                Arguments.of(new Moving(Position.from("e4"), Position.from("e8"))),
                Arguments.of(new Moving(Position.from("e4"), Position.from("c6"))),
                Arguments.of(new Moving(Position.from("e4"), Position.from("h7")))
        );
    }
}
