package model.piece;

import static model.Fixtures.A4;
import static model.Fixtures.A5;
import static model.Fixtures.A6;
import static model.Fixtures.A7;
import static model.Fixtures.A8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.stream.Stream;
import model.Camp;
import model.position.Moving;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {

    @DisplayName("이동할 수 없는 경로면 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void invalidRoute(final Moving moving) {
        final Pawn pawn = new Pawn(Camp.BLACK);

        assertAll(
                () -> assertThat(pawn.canMovable(moving)).isFalse(),
                () -> assertThatThrownBy(() -> pawn.getMoveRoute(moving))
                        .isInstanceOf(IllegalArgumentException.class)
        );

    }

    static Stream<Arguments> cantMovableParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(A6, A4)),
                Arguments.of(new Moving(A7, A4)),
                Arguments.of(new Moving(A7, A8))
        );
    }

    @DisplayName("이동할 수 있다면 경로를 반환한다.")
    @ParameterizedTest
    @MethodSource("canMovableParameterProvider")
    void canMovable(final Moving moving) {
        final Pawn pawn = new Pawn(Camp.BLACK);

        assertThat(pawn.canMovable(moving)).isTrue();
    }

    static Stream<Arguments> canMovableParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(A7, A5)),
                Arguments.of(new Moving(A6, A5))
        );
    }
}
