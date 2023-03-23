package techcourse.fp.chess.domain.piece.ordinary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static techcourse.fp.chess.domain.PieceFixtures.WHITE_PAWN;
import static techcourse.fp.chess.domain.PositionFixtures.A1;
import static techcourse.fp.chess.domain.PositionFixtures.A2;
import static techcourse.fp.chess.domain.PositionFixtures.A3;
import static techcourse.fp.chess.domain.PositionFixtures.A4;
import static techcourse.fp.chess.domain.PositionFixtures.A5;
import static techcourse.fp.chess.domain.PositionFixtures.B1;
import static techcourse.fp.chess.domain.PositionFixtures.C1;
import static techcourse.fp.chess.domain.PositionFixtures.D1;
import static techcourse.fp.chess.domain.PositionFixtures.E1;
import static techcourse.fp.chess.domain.PositionFixtures.H8;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.piece.Color;

class RookTest {

    private final Rook rook = Rook.create(Color.BLACK);

    @DisplayName("시작 지점과 목적 지점 사이의 모든 경로를 반환한다. - 우로 이동하는 경우")
    @Test
    void right_move() {
        final List<Position> path = rook.findPath(A1, A5, WHITE_PAWN);

        assertThat(path).containsExactly(A2, A3, A4);
    }

    @DisplayName("시작 지점과 목적 지점 사이의 모든 경로를 반환한다. - 좌로 이동하는 경우")
    @Test
    void left_move() {
        final List<Position> path = rook.findPath(A5, A1, WHITE_PAWN);

        assertThat(path).containsExactly(A4,A3,A2);
    }

    @DisplayName("시작 지점과 목적 지점 사이의 모든 경로를 반환한다. - 위로 이동하는 경우")
    @Test
    void up_move() {
        final List<Position> path = rook.findPath(A1, E1, WHITE_PAWN);

        assertThat(path).containsExactly(B1,C1,D1);
    }

    @DisplayName("시작 지점과 목적 지점 사이의 모든 경로를 반환한다. - 아래로 이동하는 경우")
    @Test
    void down_move() {
        final List<Position> path = rook.findPath(E1, A1, WHITE_PAWN);

        assertThat(path).containsExactly(D1,C1,B1);
    }

    @DisplayName("목적 지점이 행마법상 이동 불가능한 지역이면 예외가 발생한다.")
    @Test
    void fail_by_move_rule() {
        assertThatThrownBy(() -> rook.findPath(A1, H8, WHITE_PAWN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("행마법 상 이동할 수 없는 위치입니다.");
    }
}
