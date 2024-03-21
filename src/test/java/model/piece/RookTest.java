package model.piece;

import static model.position.Position.from;

import java.util.Set;
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

    //TODO displayName 수정

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
                Arguments.of(new Moving(from("a8"), from("b7"))),
                Arguments.of(new Moving(from("a8"), from("c4"))),
                Arguments.of(new Moving(from("b3"), from("f7"))),
                Arguments.of(new Moving(from("e3"), from("h5")))
        );
    }

    @DisplayName("이동할 수 있는 경로면 true를 반환한다.")
    @ParameterizedTest
    @MethodSource("canMovableParameterProvider")
    void canMovable(Moving moving, Set<Position> expected) {
        Rook rook = new Rook(Camp.BLACK);

        Assertions.assertThat(rook.canMovable(moving)).isTrue();
    }

    @DisplayName("이동할 수 있는 경로면 true를 반환한다.")
    @ParameterizedTest
    @MethodSource("canMovableParameterProvider")
    void checkRoute(Moving moving, Set<Position> expected) {
        Rook rook = new Rook(Camp.BLACK);

        Assertions.assertThat(rook.getRoute(moving)).isEqualTo(expected);
    }

    static Stream<Arguments> canMovableParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(from("a8"), from("a2")),
                        Set.of(from("a3"), from("a4"), from("a5"), from("a6"), from("a7"))),
                Arguments.of(new Moving(from("h8"), from("d8")), Set.of(from("g8"), from("f8"), from("e8"))),
                Arguments.of(new Moving(from("c3"), from("c1")), Set.of(from("c2"))),
                Arguments.of(new Moving(from("g2"), from("g6")), Set.of(from("g3"), from("g4"), from("g5")))
        );
    }
}
