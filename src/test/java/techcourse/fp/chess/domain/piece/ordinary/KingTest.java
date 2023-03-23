package techcourse.fp.chess.domain.piece.ordinary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static techcourse.fp.chess.domain.PieceFixtures.WHITE_PAWN;
import static techcourse.fp.chess.domain.PositionFixtures.A1;
import static techcourse.fp.chess.domain.PositionFixtures.A2;
import static techcourse.fp.chess.domain.PositionFixtures.A3;
import static techcourse.fp.chess.domain.PositionFixtures.B2;
import static techcourse.fp.chess.domain.PositionFixtures.C2;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.piece.Color;

class KingTest {

    private final King king = King.create(Color.BLACK);

    @DisplayName("시작 지점과 목적 지점 사이의 모든 경로를 반환한다. - 우상향으로 이동하는 경우")
    @Test
    void success_rightUp() {
        final List<Position> path = king.findPath(A1, B2, WHITE_PAWN);

        assertThat(path).isEmpty();
    }

    @DisplayName("시작 지점과 목적 지점 사이의 모든 경로를 반환한다. - 우로 이동하는 경우")
    @Test
    void success_right() {
        final List<Position> path = king.findPath(A1, A2, WHITE_PAWN);

        assertThat(path).isEmpty();
    }

    @DisplayName("킹은 목적 지점과 방향이 같더라도, 두 칸 이상 떨어져 있다면 예외가 발생한다.")
    @Test
    void fail_by_move_length() {
        assertThatThrownBy(() -> king.findPath(A1, A3, WHITE_PAWN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("행마법 상 이동할 수 없는 위치입니다.");
    }

    @DisplayName("목적 지점이 행마법상 이동 불가능한 지역이면 예외가 발생한다.")
    @Test
    void fail_by_move_rule() {
        assertThatThrownBy(() -> king.findPath(A1, C2, WHITE_PAWN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("행마법 상 이동할 수 없는 위치입니다.");
    }
}
