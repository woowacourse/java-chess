package model.piece;

import static model.Fixtures.A2;
import static model.Fixtures.A8;
import static model.Fixtures.B3;
import static model.Fixtures.B5;
import static model.Fixtures.B6;
import static model.Fixtures.B8;
import static model.Fixtures.C5;
import static model.Fixtures.C6;
import static model.Fixtures.C7;
import static model.Fixtures.C8;
import static model.Fixtures.D1;
import static model.Fixtures.D2;
import static model.Fixtures.D3;
import static model.Fixtures.D4;
import static model.Fixtures.D5;
import static model.Fixtures.D6;
import static model.Fixtures.D7;
import static model.Fixtures.D8;
import static model.Fixtures.E4;
import static model.Fixtures.E5;
import static model.Fixtures.E6;
import static model.Fixtures.E7;
import static model.Fixtures.E8;
import static model.Fixtures.F5;
import static model.Fixtures.F6;
import static model.Fixtures.F8;
import static model.Fixtures.G6;
import static model.Fixtures.G8;
import static model.Fixtures.H1;
import static model.Fixtures.H7;
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

class QueenTest {

    @DisplayName("이동할 수 없는 경로면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("invalidMovingParameterProvider")
    void invalidMoving(final Moving moving) {
        final Queen queen = new Queen(Camp.BLACK);

        assertAll(
                () -> assertThat(queen.canMovable(moving)).isFalse(),
                () -> assertThatThrownBy(() -> queen.getMoveRoute(moving))
                        .isInstanceOf(InvalidMovingException.class)
        );
    }

    static Stream<Arguments> invalidMovingParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(B3, B3)),
                Arguments.of(new Moving(D8, E5)),
                Arguments.of(new Moving(D6, A2)),
                Arguments.of(new Moving(B5, A8)),
                Arguments.of(new Moving(H1, C5))
        );
    }

    @DisplayName("이동 경로를 반환한다. 출발지와 도착지는 포함하지 않는다.")
    @ParameterizedTest
    @MethodSource("checkRouteParameterProvider")
    void checkRoute(final Moving moving, final Set<Position> expected) {
        final Queen queen = new Queen(Camp.BLACK);

        assertAll(
                () -> assertThat(queen.canMovable(moving)).isTrue(),
                () -> assertThat(queen.getMoveRoute(moving)).isEqualTo(expected)
        );
    }

    static Stream<Arguments> checkRouteParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(D8, D1), Set.of(D2, D3, D4, D5, D6, D7)),
                Arguments.of(new Moving(D8, A8), Set.of(B8, C8)),
                Arguments.of(new Moving(D8, H8), Set.of(E8, F8, G8)),
                Arguments.of(new Moving(D8, B6), Set.of(C7)),
                Arguments.of(new Moving(D8, F6), Set.of(E7)),
                Arguments.of(new Moving(E4, E8), Set.of(E5, E6, E7)),
                Arguments.of(new Moving(E4, C6), Set.of(D5)),
                Arguments.of(new Moving(E4, H7), Set.of(F5, G6)));

    }
}
