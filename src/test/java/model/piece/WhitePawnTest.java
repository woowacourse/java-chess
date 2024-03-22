package model.piece;

import model.position.Moving;
import model.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static model.position.Position.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class WhitePawnTest {

    @DisplayName("이동할 수 없는 경로면 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void invalidRoute(final Moving moving) {
        final Pawn whitePawn = new WhitePawn();

        assertAll(
                () -> assertThat(whitePawn.canMovable(moving)).isFalse(),
                () -> assertThatThrownBy(() -> whitePawn.getMoveRoute(moving))
                        .isInstanceOf(IllegalArgumentException.class)
        );

    }

    static Stream<Arguments> cantMovableParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(from("a3"), from("a5"))),
                Arguments.of(new Moving(from("a2"), from("a5"))),
                Arguments.of(new Moving(from("a2"), from("a1")))
        );
    }

    @DisplayName("이동할 수 있다면 경로를 반환한다.")
    @ParameterizedTest
    @MethodSource("canMovableParameterProvider")
    void canMovable(final Moving moving, final Set<Position> expected) {
        final Pawn whitePawn = new WhitePawn();

        assertAll(
                () -> assertThat(whitePawn.canMovable(moving)).isTrue(),
                () -> assertThat(whitePawn.getMoveRoute(moving)).isEqualTo(expected)
        );
    }

    static Stream<Arguments> canMovableParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(from("a2"), from("a4")), Set.of(from("a3"))),
                Arguments.of(new Moving(from("a2"), from("a3")), Set.of()),
                Arguments.of(new Moving(from("a3"), from("a4")), Set.of())
        );
    }
}
