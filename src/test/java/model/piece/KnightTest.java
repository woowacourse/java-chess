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

import exception.InvalidMovingException;
import java.util.Set;
import java.util.stream.Stream;
import model.Camp;
import model.position.Moving;
import model.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnightTest {

    @DisplayName("이동할 수 없는 경로면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("invalidMovingParameterProvider")
    void invalidMoving(final Moving moving) {
        final Knight knight = new Knight(Camp.BLACK);

        assertAll(
                () -> assertThat(knight.canMovable(moving)).isFalse(),
                () -> assertThatThrownBy(() -> knight.getMoveRoute(moving))
                        .isInstanceOf(InvalidMovingException.class)
        );
    }

    static Stream<Arguments> invalidMovingParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(D5, A5)),
                Arguments.of(new Moving(A8, F3)),
                Arguments.of(new Moving(A8, E5))
        );
    }

    @DisplayName("이동 경로를 반환한다. 출발지와 도착지는 포함하지 않는다.")
    @ParameterizedTest
    @MethodSource("checkRouteParameterProvider")
    void checkRoute(final Moving moving, final Set<Position> expected) {
        final Knight knight = new Knight(Camp.BLACK);

        assertAll(
                () -> assertThat(knight.canMovable(moving)).isTrue(),
                () -> assertThat(knight.getMoveRoute(moving)).isEqualTo(expected)
        );
    }

    static Stream<Arguments> checkRouteParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(A8, C7), Set.of()),
                Arguments.of(new Moving(A8, B6), Set.of()),
                Arguments.of(new Moving(D6, C4), Set.of()),
                Arguments.of(new Moving(D6, B5), Set.of()),
                Arguments.of(new Moving(D6, B7), Set.of()),
                Arguments.of(new Moving(D6, C8), Set.of()),
                Arguments.of(new Moving(D6, E8), Set.of()),
                Arguments.of(new Moving(D6, F7), Set.of())
        );
    }
}
