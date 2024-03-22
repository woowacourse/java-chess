package model.piece;

import static model.Fixtures.A5;
import static model.Fixtures.A8;
import static model.Fixtures.B5;
import static model.Fixtures.B6;
import static model.Fixtures.B7;
import static model.Fixtures.C4;
import static model.Fixtures.C7;
import static model.Fixtures.C8;
import static model.Fixtures.D5;
import static model.Fixtures.D6;
import static model.Fixtures.E5;
import static model.Fixtures.E8;
import static model.Fixtures.F3;
import static model.Fixtures.F7;
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

class KnightTest {

    @DisplayName("이동할 수 없는 경로면 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void invalidRoute(final Moving moving) {
        final Knight knight = new Knight(Camp.BLACK);

        assertAll(
                () -> assertThat(knight.canMovable(moving)).isFalse(),
                () -> assertThatThrownBy(() -> knight.getMoveRoute(moving))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    static Stream<Arguments> cantMovableParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(D5, A5)),
                Arguments.of(new Moving(A8, F3)),
                Arguments.of(new Moving(A8, E5))
        );
    }

    @DisplayName("이동할 수 있다면 경로를 반환한다.")
    @ParameterizedTest
    @MethodSource("canMovableParameterProvider")
    void canMovable(final Moving moving) {
        final Knight knight = new Knight(Camp.BLACK);

        assertAll(
                () -> assertThat(knight.canMovable(moving)).isTrue(),
                () -> assertThat(knight.getMoveRoute(moving)).isEmpty()
        );
    }

    static Stream<Arguments> canMovableParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(A8, C7)),
                Arguments.of(new Moving(A8, B6)),
                Arguments.of(new Moving(D6, C4)),
                Arguments.of(new Moving(D6, B5)),
                Arguments.of(new Moving(D6, B7)),
                Arguments.of(new Moving(D6, C8)),
                Arguments.of(new Moving(D6, E8)),
                Arguments.of(new Moving(D6, F7))
        );
    }
}
