package model.piece;

import static model.position.Position.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Set;
import java.util.stream.Stream;
import model.position.Moving;
import model.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WhitePawnTest {

    @DisplayName("이동할 수 없는 경로면 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void invalidMoveRoute(final Moving moving) {
        final Pawn whitePawn = new WhitePawn();

        assertAll(
                () -> assertThat(whitePawn.canMovable(moving)).isFalse(),
                () -> assertThatThrownBy(() -> whitePawn.getMoveRoute(moving))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("해당 기물이 이동할 수 없는 위치입니다.")
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

    @DisplayName("공격할 수 없는 경로면 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("cantAttackableParameterProvider")
    void invalidAttackRoute(final Moving moving) {
        final Pawn whitePawn = new WhitePawn();

        assertAll(
                () -> assertThatThrownBy(() -> whitePawn.getAttackRoute(moving))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("해당 기물이 이동할 수 없는 위치입니다.")
        );

    }

    static Stream<Arguments> cantAttackableParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(from("a2"), from("a3"))),
                Arguments.of(new Moving(from("a2"), from("b1"))),
                Arguments.of(new Moving(from("a2"), from("b4")))
        );
    }

    @DisplayName("이동할 수 있다면 경로를 반환한다.")
    @ParameterizedTest
    @MethodSource("canAttackableParameterProvider")
    void canAttackable(final Moving moving, final Set<Position> expected) {
        final Pawn whitePawn = new WhitePawn();

        assertAll(
                () -> assertThat(whitePawn.getAttackRoute(moving)).isEqualTo(expected)
        );
    }

    static Stream<Arguments> canAttackableParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(from("a2"), from("b3")), Set.of()),
                Arguments.of(new Moving(from("d3"), from("c4")), Set.of()),
                Arguments.of(new Moving(from("f7"), from("e8")), Set.of())
        );
    }
}
