package model.piece;

import static model.Fixtures.A8;
import static model.Fixtures.B5;
import static model.Fixtures.D2;
import static model.Fixtures.D3;
import static model.Fixtures.D6;
import static model.Fixtures.D7;
import static model.Fixtures.D8;
import static model.Fixtures.E1;
import static model.Fixtures.E2;
import static model.Fixtures.E7;
import static model.Fixtures.E8;
import static model.Fixtures.F2;
import static model.Fixtures.F5;
import static model.Fixtures.F7;
import static model.Fixtures.F8;
import static model.Fixtures.G4;
import static model.Fixtures.H2;
import static model.Fixtures.H6;
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

class KingTest {

    @DisplayName("이동할 수 없는 경로면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("invalidMovingParameterProvider")
    void invalidMoving(final Moving moving) {
        final King king = new King(Camp.BLACK);

        assertAll(
                () -> assertThat(king.canMovable(moving)).isFalse(),
                () -> assertThatThrownBy(() -> king.getMoveRoute(moving))
                        .isInstanceOf(InvalidMovingException.class)
        );
    }

    static Stream<Arguments> invalidMovingParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(D6, B5)),
                Arguments.of(new Moving(F5, A8)),
                Arguments.of(new Moving(D3, H2)),
                Arguments.of(new Moving(H6, G4))
        );
    }

    @DisplayName("이동 경로를 반환한다. 출발지와 도착지는 포함하지 않는다.")
    @ParameterizedTest
    @MethodSource("checkRouteParameterProvider")
    void checkRoute(final Moving moving, final Set<Position> expected) {
        final King king = new King(Camp.BLACK);

        assertAll(
                () -> assertThat(king.canMovable(moving)).isTrue(),
                () -> assertThat(king.getMoveRoute(moving)).isEqualTo(expected)
        );
    }

    static Stream<Arguments> checkRouteParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(E8, D8), Set.of()),
                Arguments.of(new Moving(E8, E7), Set.of()),
                Arguments.of(new Moving(E8, D7), Set.of()),
                Arguments.of(new Moving(E8, F7), Set.of()),
                Arguments.of(new Moving(E8, F8), Set.of()),
                Arguments.of(new Moving(E1, D2), Set.of()),
                Arguments.of(new Moving(E1, E2), Set.of()),
                Arguments.of(new Moving(E1, F2), Set.of())
        );
    }
}
