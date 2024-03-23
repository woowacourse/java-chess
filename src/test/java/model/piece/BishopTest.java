package model.piece;

import static model.Fixtures.A3;
import static model.Fixtures.A6;
import static model.Fixtures.A8;
import static model.Fixtures.B4;
import static model.Fixtures.B5;
import static model.Fixtures.C3;
import static model.Fixtures.C4;
import static model.Fixtures.C5;
import static model.Fixtures.C8;
import static model.Fixtures.D5;
import static model.Fixtures.D6;
import static model.Fixtures.D7;
import static model.Fixtures.E6;
import static model.Fixtures.E7;
import static model.Fixtures.E8;
import static model.Fixtures.F7;
import static model.Fixtures.F8;
import static model.Fixtures.G4;
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

class BishopTest {

    @DisplayName("이동할 수 없는 경로면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("invalidMovingParameterProvider")
    void invalidMoving(final Moving moving) {
        final Bishop bishop = new Bishop(Camp.BLACK);

        assertAll(
                () -> assertThat(bishop.canMovable(moving)).isFalse(),
                () -> assertThatThrownBy(() -> bishop.getMoveRoute(moving))
                        .isInstanceOf(InvalidMovingException.class)
        );
    }

    static Stream<Arguments> invalidMovingParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(C8, A8)),
                Arguments.of(new Moving(C8, C3)),
                Arguments.of(new Moving(F8, E8)),
                Arguments.of(new Moving(G4, C3))
        );
    }

    @DisplayName("이동 경로를 반환한다. 출발지와 도착지는 포함하지 않는다.")
    @ParameterizedTest
    @MethodSource("checkRouteParameterProvider")
    void checkRoute(final Moving moving, final Set<Position> expected) {
        final Bishop bishop = new Bishop(Camp.BLACK);

        assertAll(
                () -> assertThat(bishop.canMovable(moving)).isTrue(),
                () -> assertThat(bishop.getMoveRoute(moving)).isEqualTo(expected)
        );

    }

    static Stream<Arguments> checkRouteParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(C8, E6), Set.of(D7)),
                Arguments.of(new Moving(F8, A3), Set.of(E7, D6, C5, B4)),
                Arguments.of(new Moving(C4, A6), Set.of(B5)),
                Arguments.of(new Moving(C4, F7), Set.of(D5, E6))
        );
    }
}
