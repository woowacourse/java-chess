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

import static chess.fixture.PieceFixture.BLACK_KING;
import static chess.fixture.PositionFixture.C3;
import static chess.fixture.PositionFixture.C4;
import static chess.fixture.PositionFixture.C5;
import static chess.fixture.PositionFixture.D3;
import static chess.fixture.PositionFixture.D4;
import static chess.fixture.PositionFixture.D5;
import static chess.fixture.PositionFixture.E3;
import static chess.fixture.PositionFixture.E4;
import static chess.fixture.PositionFixture.E5;
import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {

    /*
    ........  8
    ........  7
    ........  6
    ..***...  5
    ..*K*...  4
    ..***...  3
    ........  2
    ........  1

    abcdefgh
     */
    @DisplayName("킹은 수직, 수평 또는 대각선 방향으로 한 칸 움직인다.")
    @ParameterizedTest
    @MethodSource("movableTargets")
    void hasFollowedRule(Position target) {
        boolean actual = BLACK_KING.hasFollowedRule(D4, target, MovePathFixture.noPieces());

        assertThat(actual).isTrue();
    }

    private static Stream<Arguments> movableTargets() {
        return PositionFixture.movablePositions(List.of(C3, C4, C5, D3, D5, E3, E4, E5));
    }

    @DisplayName("킹은 수직, 수평 또는 대각선 방향으로 한 칸을 제외하고 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("immovableTargets")
    void hasViolatedRule(Position target) {
        boolean actual = BLACK_KING.hasFollowedRule(D4, target, MovePathFixture.noPieces());

        assertThat(actual).isFalse();
    }

    private static Stream<Arguments> immovableTargets() {
        return PositionFixture.immovablePositions(List.of(C3, C4, C5, D3, D5, E3, E4, E5), D4);
    }
}
