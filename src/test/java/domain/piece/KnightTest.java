package domain.piece;

import static fixture.PositionFixture.B3;
import static fixture.PositionFixture.B5;
import static fixture.PositionFixture.C2;
import static fixture.PositionFixture.C6;
import static fixture.PositionFixture.D4;
import static fixture.PositionFixture.E2;
import static fixture.PositionFixture.E6;
import static fixture.PositionFixture.F3;
import static fixture.PositionFixture.F5;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Knight;
import chess.domain.piece.Side;
import chess.domain.square.Square;
import fixture.MovePathFixture;
import fixture.PositionFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class KnightTest {

    /*
    ........  8
    ........  7
    ..*.*...  6
    .*...*..  5
    ...N....  4
    .*...*..  3
    ..*.*...  2
    ........  1

    abcdefgh
     */
    private static final Square SOURCE = D4;
    private static final List<Square> MOVABLE_SQUARES = List.of(B3, B5, C2, C6, E2, E6, F3, F5);

    private static Stream<Arguments> movableTargets() {
        return PositionFixture.movablePositions(MOVABLE_SQUARES);
    }

    private static Stream<Arguments> immovableTargets() {
        return PositionFixture.immovablePositions(MOVABLE_SQUARES, SOURCE);
    }

    @DisplayName("나이트는 수평으로 두 칸 수직으로 한 칸, 또는 수직으로 두 칸 수평으로 한 칸 움직인다.")
    @ParameterizedTest
    @MethodSource("movableTargets")
    void hasFollowedRule(Square target) {
        Knight knight = new Knight(Side.BLACK);

        boolean actual = knight.hasFollowedRule(SOURCE, target, MovePathFixture.noPieces());

        assertThat(actual).isTrue();
    }

    @DisplayName("나이트는 수평으로 두 칸 수직으로 한 칸, 또는 수직으로 두 칸 수평으로 한 칸을 제외하고 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("immovableTargets")
    void hasViolatedRule(Square target) {
        Knight knight = new Knight(Side.BLACK);

        boolean actual = knight.hasFollowedRule(SOURCE, target, MovePathFixture.noPieces());

        assertThat(actual).isFalse();
    }
}
