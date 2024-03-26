package chess.domain.piece;

import static chess.fixture.PieceFixture.BLACK_KNIGHT;
import static chess.fixture.PositionFixture.B3;
import static chess.fixture.PositionFixture.B5;
import static chess.fixture.PositionFixture.C2;
import static chess.fixture.PositionFixture.C6;
import static chess.fixture.PositionFixture.D4;
import static chess.fixture.PositionFixture.E2;
import static chess.fixture.PositionFixture.E6;
import static chess.fixture.PositionFixture.F3;
import static chess.fixture.PositionFixture.F5;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import chess.fixture.MovePathFixture;
import chess.fixture.PositionFixture;
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
    private static final Position SOURCE = D4;
    private static final List<Position> MOVABLE_POSITIONS = List.of(B3, B5, C2, C6, E2, E6, F3, F5);

    private static Stream<Arguments> movableTargets() {
        return PositionFixture.movablePositions(MOVABLE_POSITIONS);
    }

    private static Stream<Arguments> immovableTargets() {
        return PositionFixture.immovablePositions(MOVABLE_POSITIONS, SOURCE);
    }

    @DisplayName("나이트는 수평으로 두 칸 수직으로 한 칸, 또는 수직으로 두 칸 수평으로 한 칸 움직인다.")
    @ParameterizedTest
    @MethodSource("movableTargets")
    void hasFollowedRule(Position target) {
        boolean actual = BLACK_KNIGHT.hasFollowedRule(SOURCE, target, MovePathFixture.noPieces());

        assertThat(actual).isTrue();
    }

    @DisplayName("나이트는 수평으로 두 칸 수직으로 한 칸, 또는 수직으로 두 칸 수평으로 한 칸을 제외하고 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("immovableTargets")
    void hasViolatedRule(Position target) {
        boolean actual = BLACK_KNIGHT.hasFollowedRule(SOURCE, target, MovePathFixture.noPieces());

        assertThat(actual).isFalse();
    }
}
