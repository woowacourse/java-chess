package domain.piece;

import domain.Position;
import domain.Side;
import fixture.MovePathFixture;
import fixture.PositionFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static fixture.PositionFixture.A1;
import static fixture.PositionFixture.A4;
import static fixture.PositionFixture.A7;
import static fixture.PositionFixture.B2;
import static fixture.PositionFixture.B4;
import static fixture.PositionFixture.B6;
import static fixture.PositionFixture.C3;
import static fixture.PositionFixture.C4;
import static fixture.PositionFixture.C5;
import static fixture.PositionFixture.D1;
import static fixture.PositionFixture.D2;
import static fixture.PositionFixture.D3;
import static fixture.PositionFixture.D4;
import static fixture.PositionFixture.D5;
import static fixture.PositionFixture.D6;
import static fixture.PositionFixture.D7;
import static fixture.PositionFixture.D8;
import static fixture.PositionFixture.E3;
import static fixture.PositionFixture.E4;
import static fixture.PositionFixture.E5;
import static fixture.PositionFixture.F2;
import static fixture.PositionFixture.F4;
import static fixture.PositionFixture.F6;
import static fixture.PositionFixture.G1;
import static fixture.PositionFixture.G4;
import static fixture.PositionFixture.G7;
import static fixture.PositionFixture.H4;
import static fixture.PositionFixture.H8;
import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {

    /*
    ...*...*  8
    *..*..*.  7
    .*.*.*..  6
    ..***...  5
    ***Q****  4
    ..***...  3
    .*.*.*..  2
    *..*..*.  1

    abcdefgh
     */
    private static final Position SOURCE = D4;
    private static final List<Position> MOVABLE_POSITIONS = List.of(
            A4, B4, C4, D1, D2, D3, D5, D6, D7, D8, E4, F4, G4, H4,
            A1, A7, B2, B6, C3, C5, E3, E5, F2, F6, G1, G7, H8
    );

    private static Stream<Arguments> movableTargets() {
        return PositionFixture.movablePositions(MOVABLE_POSITIONS);
    }

    private static Stream<Arguments> immovableTargets() {
        return PositionFixture.immovablePositions(MOVABLE_POSITIONS, SOURCE);
    }

    @DisplayName("퀸은 수직, 수평 또는 대각선 방향으로 한 칸 이상 움직인다.")
    @ParameterizedTest
    @MethodSource("movableTargets")
    void hasFollowedRule(Position target) {
        Queen queen = new Queen(Side.BLACK);

        boolean actual = queen.hasFollowedRule(SOURCE, target, MovePathFixture.noPieces());

        assertThat(actual).isTrue();
    }

    @DisplayName("퀸은 수직, 수평 또는 대각선 방향을 제외하고 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("immovableTargets")
    void hasViolatedRule(Position target) {
        Queen queen = new Queen(Side.BLACK);

        boolean actual = queen.hasFollowedRule(SOURCE, target, MovePathFixture.noPieces());

        assertThat(actual).isFalse();
    }
}
