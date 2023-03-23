package techcourse.fp.chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static techcourse.fp.chess.domain.PieceFixtures.WHITE_PAWN;
import static techcourse.fp.chess.domain.PositionFixtures.A1;
import static techcourse.fp.chess.domain.PositionFixtures.A2;
import static techcourse.fp.chess.domain.PositionFixtures.A3;
import static techcourse.fp.chess.domain.PositionFixtures.A4;
import static techcourse.fp.chess.domain.PositionFixtures.A5;
import static techcourse.fp.chess.domain.PositionFixtures.A6;
import static techcourse.fp.chess.domain.PositionFixtures.A7;
import static techcourse.fp.chess.domain.PositionFixtures.A8;
import static techcourse.fp.chess.domain.PositionFixtures.B2;
import static techcourse.fp.chess.domain.PositionFixtures.C2;
import static techcourse.fp.chess.domain.PositionFixtures.C3;
import static techcourse.fp.chess.domain.PositionFixtures.D4;
import static techcourse.fp.chess.domain.PositionFixtures.E5;
import static techcourse.fp.chess.domain.PositionFixtures.F6;
import static techcourse.fp.chess.domain.PositionFixtures.G7;
import static techcourse.fp.chess.domain.PositionFixtures.H8;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import techcourse.fp.chess.domain.Position;

class QueenTest {

    private final Queen queen = Queen.create(Color.BLACK);

    @DisplayName("시작 지점과 목적 지점 사이의 모든 경로를 반환한다. - 우상향으로 이동하는 경우")
    @Test
    void success_rightUp() {
        final List<Position> path = queen.findPath(A1, H8, WHITE_PAWN);

        assertThat(path).containsExactly(B2, C3, D4, E5, F6, G7);
    }

    @DisplayName("시작 지점과 목적 지점 사이의 모든 경로를 반환한다. - 우로 이동하는 경우")
    @Test
    void success_right() {
        final List<Position> path = queen.findPath(A1, A8, WHITE_PAWN);

        assertThat(path).containsExactly(A2, A3, A4, A5, A6, A7);
    }

    @DisplayName("목적 지점이 행마법상 이동 불가능한 지역이면 예외가 발생한다.")
    @Test
    void fail_by_move_rule() {
        assertThatThrownBy(() -> queen.findPath(A1, C2, WHITE_PAWN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("행마법 상 이동할 수 없는 위치입니다.");
    }
}
