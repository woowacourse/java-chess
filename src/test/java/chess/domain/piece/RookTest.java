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

import static chess.fixture.PieceFixture.BLACK_ROOK;
import static chess.fixture.PositionFixture.A4;
import static chess.fixture.PositionFixture.B4;
import static chess.fixture.PositionFixture.C4;
import static chess.fixture.PositionFixture.D1;
import static chess.fixture.PositionFixture.D2;
import static chess.fixture.PositionFixture.D3;
import static chess.fixture.PositionFixture.D4;
import static chess.fixture.PositionFixture.D5;
import static chess.fixture.PositionFixture.D6;
import static chess.fixture.PositionFixture.D7;
import static chess.fixture.PositionFixture.D8;
import static chess.fixture.PositionFixture.E4;
import static chess.fixture.PositionFixture.F4;
import static chess.fixture.PositionFixture.G4;
import static chess.fixture.PositionFixture.H4;
import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    /*
    ...*....  8
    ...*....  7
    ...*....  6
    ...*....  5
    ***R****  4
    ...*....  3
    ...*....  2
    ...*....  1

    abcdefgh
     */
    private static final Position SOURCE = D4;
    private static final List<Position> MOVABLE_POSITIONS = List.of(A4, B4, C4, D1, D2, D3, D5, D6, D7, D8, E4, F4, G4, H4);

    private static Stream<Arguments> movableTargets() {
        return PositionFixture.movablePositions(MOVABLE_POSITIONS);
    }

    private static Stream<Arguments> immovableTargets() {
        return PositionFixture.immovablePositions(MOVABLE_POSITIONS, SOURCE);
    }

    @DisplayName("룩은 수직 또는 수평 방향으로 한 칸 이상 움직인다.")
    @ParameterizedTest
    @MethodSource("movableTargets")
    void hasFollowedRule(Position target) {
        boolean actual = BLACK_ROOK.hasFollowedRule(SOURCE, target, MovePathFixture.noPieces());

        assertThat(actual).isTrue();
    }

    @DisplayName("룩은 수직 또는 수평 방향을 제외하고 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("immovableTargets")
    void hasViolatedRule(Position target) {
        boolean actual = BLACK_ROOK.hasFollowedRule(SOURCE, target, MovePathFixture.noPieces());

        assertThat(actual).isFalse();
    }
}
