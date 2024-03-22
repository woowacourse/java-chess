package domain;

import fixture.MovePathFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static fixture.PositionFixture.A1;
import static fixture.PositionFixture.A2;
import static fixture.PositionFixture.A3;
import static fixture.PositionFixture.B1;
import static fixture.PositionFixture.B2;
import static fixture.PositionFixture.C1;
import static fixture.PositionFixture.B3;
import static fixture.PositionFixture.C2;
import static fixture.PositionFixture.C3;
import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    /*
    *..  3
    *..  2
    r**  1
    abc
     */
    private static Stream<Arguments> movableTargetPosition() {
        return Stream.of(
                Arguments.arguments(A2()),
                Arguments.arguments(A3()),
                Arguments.arguments(B1()),
                Arguments.arguments(C1())
        );
    }

    /*
    .**  3
    .**  2
    r..  1
    abc
     */
    private static Stream<Arguments> immovableTargetPosition() {
        return Stream.of(
                Arguments.arguments(B2()),
                Arguments.arguments(C2()),
                Arguments.arguments(B3()),
                Arguments.arguments(C3())
        );
    }

    @DisplayName("룩은 수직 또는 수평 방향으로 한 칸 이상 움직인다.")
    @ParameterizedTest
    @MethodSource("movableTargetPosition")
    void canMoveTest(Position target) {
        Rook rook = new Rook(Side.WHITE);
        Position source = A1();

        boolean actual = rook.hasFollowedRule(source, target, MovePathFixture.noPieces());

        assertThat(actual).isTrue();
    }

    @DisplayName("룩은 수직 또는 수평 방향으로 한 칸 이상을 제외하고는 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("immovableTargetPosition")
    void cantMoveTest(Position target) {
        Rook rook = new Rook(Side.BLACK);
        Position source = A1();

        boolean actual = rook.hasFollowedRule(source, target, MovePathFixture.noPieces());

        assertThat(actual).isFalse();
    }
}
