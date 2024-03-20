package model.piece;

import java.util.stream.Stream;
import model.Camp;
import model.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {

    @DisplayName("이동할 수 없는 경로면 false를 반환한다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void cantMovable(final Position currentPosition, final Position nextPosition) {
        Pawn pawn = new Pawn(Camp.BLACK);

        Assertions.assertThat(pawn.canMovable(currentPosition, nextPosition)).isFalse();
    }

    @DisplayName("이동할 수 없는 경로면 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void invalidRoute(final Position currentPosition, final Position nextPosition) {
        Pawn pawn = new Pawn(Camp.BLACK);

        Assertions.assertThatThrownBy(() -> pawn.getRoute(currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> cantMovableParameterProvider() {
        return Stream.of(
                Arguments.of(Position.from("a6"), Position.from("a4")),
                Arguments.of(Position.from("a7"), Position.from("a4")),
                Arguments.of(Position.from("a7"), Position.from("a8"))
        );
    }

    @DisplayName("이동할 수 있는 경로면 true를 반환한다.")
    @ParameterizedTest
    @MethodSource("canMovableParameterProvider")
    void canMovable(final Position currentPosition, final Position nextPosition) {
        Pawn pawn = new Pawn(Camp.BLACK);

        Assertions.assertThat(pawn.canMovable(currentPosition, nextPosition)).isTrue();
    }

    static Stream<Arguments> canMovableParameterProvider() {
        return Stream.of(
                Arguments.of(Position.from("a7"), Position.from("a5")),
                Arguments.of(Position.from("a6"), Position.from("a5"))
        );
    }


}
