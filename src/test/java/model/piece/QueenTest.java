package model.piece;

import static model.position.Position.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Set;
import java.util.stream.Stream;
import model.Camp;
import model.position.Moving;
import model.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class QueenTest {

    @DisplayName("이동할 수 없는 경로면 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void invalidRoute(Moving moving) {
        Queen queen = new Queen(Camp.BLACK);

        assertAll(
                () -> assertThat(queen.canMovable(moving)).isFalse(),
                () -> assertThatThrownBy(() -> queen.getRoute(moving))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    static Stream<Arguments> cantMovableParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(from("b3"), from("b3"))),
                Arguments.of(new Moving(from("d8"), from("e5"))),
                Arguments.of(new Moving(from("d6"), from("a2"))),
                Arguments.of(new Moving(from("b5"), from("a8"))),
                Arguments.of(new Moving(from("h1"), from("c5")))
        );
    }

    @DisplayName("이동할 수 있다면 경로를 반환한다.")
    @ParameterizedTest
    @MethodSource("canMovableParameterProvider")
    void checkRoute(Moving moving, Set<Position> expected) {
        Queen queen = new Queen(Camp.BLACK);

        assertAll(
                () -> assertThat(queen.canMovable(moving)).isTrue(),
                () -> assertThat(queen.getRoute(moving)).isEqualTo(expected)
        );

    }

    static Stream<Arguments> canMovableParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(from("d8"), from("d1")),
                        Set.of(from("d2"), from("d3"), from("d4"),
                                from("d5"), from("d6"), from("d7"))),
                Arguments.of(new Moving(from("d8"), from("a8")),
                        Set.of(from("b8"), from("c8"))),
                Arguments.of(new Moving(from("d8"), from("h8")),
                        Set.of(from("e8"), from("f8"), from("g8"))),
                Arguments.of(new Moving(from("d8"), from("b6")),
                        Set.of(from("c7"))),
                Arguments.of(new Moving(from("d8"), from("f6")),
                        Set.of(from("e7"))),
                Arguments.of(new Moving(from("e4"), from("e8")),
                        Set.of(from("e5"), from("e6"), from("e7"))),
                Arguments.of(new Moving(from("e4"), from("c6")),
                        Set.of(from("d5"))),
                Arguments.of(new Moving(from("e4"), from("h7")),
                        Set.of(from("f5"), from("g6")))
        );
    }
}
