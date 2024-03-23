package model.piece;

import static model.Fixtures.A2;
import static model.Fixtures.A3;
import static model.Fixtures.A4;
import static model.Fixtures.A5;
import static model.Fixtures.A6;
import static model.Fixtures.A7;
import static model.Fixtures.A8;
import static model.Fixtures.B3;
import static model.Fixtures.B7;
import static model.Fixtures.C1;
import static model.Fixtures.C2;
import static model.Fixtures.C3;
import static model.Fixtures.C4;
import static model.Fixtures.D8;
import static model.Fixtures.E3;
import static model.Fixtures.E8;
import static model.Fixtures.F7;
import static model.Fixtures.F8;
import static model.Fixtures.G2;
import static model.Fixtures.G3;
import static model.Fixtures.G4;
import static model.Fixtures.G5;
import static model.Fixtures.G6;
import static model.Fixtures.G8;
import static model.Fixtures.H5;
import static model.Fixtures.H8;
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

class RookTest {

    @DisplayName("이동할 수 없는 경로면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("invalidMovingParameterProvider")
    void invalidMoving(final Moving moving) {
        final Rook rook = new Rook(Camp.BLACK);

        assertAll(
                () -> assertThat(rook.canMovable(moving)).isFalse(),
                () -> assertThatThrownBy(() -> rook.getMoveRoute(moving))
                        .isInstanceOf(InvalidMovingException.class)
        );
    }

    static Stream<Arguments> invalidMovingParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(A8, B7)),
                Arguments.of(new Moving(A8, C4)),
                Arguments.of(new Moving(B3, F7)),
                Arguments.of(new Moving(E3, H5))
        );
    }

    @DisplayName("이동 경로를 반환한다. 출발지와 도착지는 포함하지 않는다.")
    @ParameterizedTest
    @MethodSource("checkRouteParameterProvider")
    void checkRoute(final Moving moving, final Set<Position> expected) {
        final Rook rook = new Rook(Camp.BLACK);

        assertAll(
                () -> assertThat(rook.canMovable(moving)).isTrue(),
                () -> assertThat(rook.getMoveRoute(moving)).isEqualTo(expected)
        );
    }

    static Stream<Arguments> checkRouteParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(A8, A2), Set.of(A3, A4, A5, A6, A7)),
                Arguments.of(new Moving(H8, D8), Set.of(G8, F8, E8)),
                Arguments.of(new Moving(C3, C1), Set.of(C2)),
                Arguments.of(new Moving(G2, G6), Set.of(G3, G4, G5))
        );
    }
}
