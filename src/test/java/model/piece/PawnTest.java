package model.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.stream.Stream;
import model.Camp;
import model.position.Moving;
import model.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {

    @DisplayName("이동할 수 없는 경로면 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void invalidRoute(Moving moving) {
        Pawn pawn = new Pawn(Camp.BLACK);

        assertAll(
                () -> assertThat(pawn.canMovable(moving)).isFalse(),
                () -> assertThatThrownBy(() -> pawn.getRoute(moving))
                        .isInstanceOf(IllegalArgumentException.class)
        );

    }

    static Stream<Arguments> cantMovableParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(Position.from("a6"), Position.from("a4"))),
                Arguments.of(new Moving(Position.from("a7"), Position.from("a4"))),
                Arguments.of(new Moving(Position.from("a7"), Position.from("a8")))
        );
    }

    @DisplayName("이동할 수 있다면 경로를 반환한다.")
    @ParameterizedTest
    @MethodSource("canMovableParameterProvider")
    void canMovable(Moving moving) {
        Pawn pawn = new Pawn(Camp.BLACK);

        assertThat(pawn.canMovable(moving)).isTrue();
    }

    static Stream<Arguments> canMovableParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(Position.from("a7"), Position.from("a5"))),
                Arguments.of(new Moving(Position.from("a6"), Position.from("a5")))
        );
    }
}
