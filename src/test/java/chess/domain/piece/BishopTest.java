package chess.domain.piece;

import chess.domain.position.Position;
import chess.fixture.MovePathFixture;
import chess.fixture.PositionFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.fixture.PieceFixture.BLACK_BISHOP;
import static chess.fixture.PositionFixture.A1;
import static chess.fixture.PositionFixture.A7;
import static chess.fixture.PositionFixture.B2;
import static chess.fixture.PositionFixture.B6;
import static chess.fixture.PositionFixture.C3;
import static chess.fixture.PositionFixture.C5;
import static chess.fixture.PositionFixture.D4;
import static chess.fixture.PositionFixture.E3;
import static chess.fixture.PositionFixture.E5;
import static chess.fixture.PositionFixture.F2;
import static chess.fixture.PositionFixture.F6;
import static chess.fixture.PositionFixture.G1;
import static chess.fixture.PositionFixture.G7;
import static chess.fixture.PositionFixture.H8;
import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    /*
    .......*  8
    *.....*.  7
    .*...*..  6
    ..*.*...  5
    ...B....  4
    ..*.*...  3
    .*...*..  2
    *.....*.  1

    abcdefgh
     */
    private static final Position SOURCE = D4;
    private static final List<Position> MOVABLE_POSITIONS = List.of(A1, A7, B2, B6, C3, C5, E3, E5, F2, F6, G1, G7, H8);

    private static Stream<Arguments> movableTargets() {
        return PositionFixture.movablePositions(MOVABLE_POSITIONS);
    }

    private static Stream<Arguments> immovableTargets() {
        return PositionFixture.immovablePositions(MOVABLE_POSITIONS, SOURCE);
    }

    @DisplayName("비숍은 대각선 방향으로 한 칸 이상 움직인다.")
    @ParameterizedTest
    @MethodSource("movableTargets")
    void hasFollowedRule(Position target) {
        boolean actual = BLACK_BISHOP.hasFollowedRule(SOURCE, target, MovePathFixture.noPieces());

        assertThat(actual).isTrue();
    }

    @DisplayName("비숍은 대각선 방향을 제외하고 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("immovableTargets")
    void hasViolatedRule(Position target) {
        boolean actual = BLACK_BISHOP.hasFollowedRule(SOURCE, target, MovePathFixture.noPieces());

        assertThat(actual).isFalse();
    }
}
