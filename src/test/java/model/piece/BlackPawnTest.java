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

class BlackPawnTest {

    @DisplayName("이동할 수 없는 경로면 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void invalidRoute(final Moving moving) {
        final Pawn blackPawn = new BlackPawn();

        assertAll(
                () -> assertThat(blackPawn.canMovable(moving)).isFalse(),
                () -> assertThatThrownBy(() -> blackPawn.getMoveRoute(moving))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("해당 기물이 이동할 수 없는 위치입니다.")
        );

    }

    static Stream<Arguments> cantMovableParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(from("a6"), from("a4"))),
                Arguments.of(new Moving(from("a7"), from("a4"))),
                Arguments.of(new Moving(from("a7"), from("a8")))
        );
    }

    @DisplayName("이동할 수 있다면 경로를 반환한다.")
    @ParameterizedTest
    @MethodSource("canMovableParameterProvider")
    void canMovable(final Moving moving, final Set<Position> expected) {
        final Pawn blackPawn = new BlackPawn();

        assertAll(
                () -> assertThat(blackPawn.canMovable(moving)).isTrue(),
                () -> assertThat(blackPawn.getMoveRoute(moving)).isEqualTo(expected)
        );
    }

    static Stream<Arguments> canMovableParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(from("a7"), from("a5")), Set.of(from("a6"))),
                Arguments.of(new Moving(from("a7"), from("a6")), Set.of()),
                Arguments.of(new Moving(from("a6"), from("a5")), Set.of())
        );
    }
}
