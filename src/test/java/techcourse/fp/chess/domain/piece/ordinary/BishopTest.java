package techcourse.fp.chess.domain.piece.ordinary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static techcourse.fp.chess.domain.PieceFixtures.WHITE_PAWN;
import static techcourse.fp.chess.domain.PositionFixtures.A1;
import static techcourse.fp.chess.domain.PositionFixtures.A5;
import static techcourse.fp.chess.domain.PositionFixtures.B2;
import static techcourse.fp.chess.domain.PositionFixtures.B4;
import static techcourse.fp.chess.domain.PositionFixtures.C2;
import static techcourse.fp.chess.domain.PositionFixtures.C3;
import static techcourse.fp.chess.domain.PositionFixtures.D2;
import static techcourse.fp.chess.domain.PositionFixtures.D4;
import static techcourse.fp.chess.domain.PositionFixtures.E1;
import static techcourse.fp.chess.domain.PositionFixtures.E5;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.piece.Color;

class BishopTest {

    private final Bishop bishop = Bishop.create(Color.BLACK);

    @DisplayName("시작 지점과 목적 지점 사이의 모든 경로를 반환한다. - 우상으로 이동하는 경우")
    @Test
    void up_right_move() {
        final List<Position> path = bishop.findPath(A1, E5, WHITE_PAWN);

        assertThat(path).containsExactly(B2, C3, D4);
    }

    @DisplayName("시작 지점과 목적 지점 사이의 모든 경로를 반환한다. - 좌상으로 이동하는 경우")
    @Test
    void up_left_move() {
        final List<Position> path = bishop.findPath(E1, A5, WHITE_PAWN);

        assertThat(path).containsExactly(D2, C3, B4);
    }

    @DisplayName("시작 지점과 목적 지점 사이의 모든 경로를 반환한다. - 우하로 이동하는 경우")
    @Test
    void down_right_move() {
        final List<Position> path = bishop.findPath(A5, E1, WHITE_PAWN);

        assertThat(path).containsExactly(B4, C3, D2);
    }

    @DisplayName("시작 지점과 목적 지점 사이의 모든 경로를 반환한다. - 좌하로 이동하는 경우")
    @Test
    void down_left_move() {
        final List<Position> path = bishop.findPath(E5, A1, WHITE_PAWN);

        assertThat(path).containsExactly(D4, C3, B2);
    }

    @DisplayName("목적 지점이 행마법상 이동 불가능한 지역이면 예외가 발생한다.")
    @Test
    void fail_by_move_rule() {
        assertThatThrownBy(() -> bishop.findPath(A1, C2, WHITE_PAWN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage( "행마법 상 이동할 수 없는 위치입니다.");
    }
}
