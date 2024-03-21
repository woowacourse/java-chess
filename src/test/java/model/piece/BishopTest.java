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

class BishopTest {

    @DisplayName("이동할 수 없는 경로면 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void invalidRoute(final Moving moving) {
        final Bishop bishop = new Bishop(Camp.BLACK);

        assertAll(
                () -> assertThat(bishop.canMovable(moving)).isFalse(),
                () -> assertThatThrownBy(() -> bishop.getMoveRoute(moving))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    static Stream<Arguments> cantMovableParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(from("c8"), from("a8"))),
                Arguments.of(new Moving(from("c8"), from("c3"))),
                Arguments.of(new Moving(from("f8"), from("e8"))),
                Arguments.of(new Moving(from("g4"), from("c3")))
        );
    }

    @DisplayName("이동할 수 있다면 경로를 반환한다.")
    @ParameterizedTest
    @MethodSource("canMovableParameterProvider")
    void checkRoute(final Moving moving, final Set<Position> expected) {
        final Bishop bishop = new Bishop(Camp.BLACK);

        assertAll(
                () -> assertThat(bishop.canMovable(moving)).isTrue(),
                () -> assertThat(bishop.getMoveRoute(moving)).isEqualTo(expected)
        );

    }

    static Stream<Arguments> canMovableParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(from("c8"), from("e6")), Set.of(from("d7"))),
                Arguments.of(new Moving(from("f8"), from("a3")),
                        Set.of(from("e7"), from("d6"), from("c5"), from("b4"))),
                Arguments.of(new Moving(from("c4"), from("a6")), Set.of(from("b5"))),
                Arguments.of(new Moving(from("c4"), from("f7")), Set.of(from("d5"), from("e6")))
        );
    }
}
