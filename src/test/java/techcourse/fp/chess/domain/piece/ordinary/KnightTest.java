package techcourse.fp.chess.domain.piece.ordinary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static techcourse.fp.chess.domain.PieceFixtures.WHITE_PAWN;
import static techcourse.fp.chess.domain.PositionFixtures.A1;
import static techcourse.fp.chess.domain.PositionFixtures.A3;
import static techcourse.fp.chess.domain.PositionFixtures.B1;
import static techcourse.fp.chess.domain.PositionFixtures.B4;
import static techcourse.fp.chess.domain.PositionFixtures.C2;
import static techcourse.fp.chess.domain.PositionFixtures.C3;
import static techcourse.fp.chess.domain.PositionFixtures.D1;
import static techcourse.fp.chess.domain.PositionFixtures.D4;
import static techcourse.fp.chess.domain.PositionFixtures.E1;
import static techcourse.fp.chess.domain.PositionFixtures.E3;
import static techcourse.fp.chess.domain.PositionFixtures.H8;
import static techcourse.fp.chess.domain.piece.Color.BLACK;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    private final Knight knight = Knight.create(BLACK);

    @DisplayName("시작 지점과 목적 지점 사이의 모든 경로를 반환한다.")
    @Test
    void success_rightUp() {
        assertAll(
                () -> assertThat(knight.findPath(C2, B4, WHITE_PAWN)).isEmpty(),
                () -> assertThat(knight.findPath(C2, D4, WHITE_PAWN)).isEmpty(),
                () -> assertThat(knight.findPath(C2, E3, WHITE_PAWN)).isEmpty(),
                () -> assertThat(knight.findPath(C2, E1, WHITE_PAWN)).isEmpty(),
                () -> assertThat(knight.findPath(C3, D1, WHITE_PAWN)).isEmpty(),
                () -> assertThat(knight.findPath(C3, B1, WHITE_PAWN)).isEmpty(),
                () -> assertThat(knight.findPath(C2, A1, WHITE_PAWN)).isEmpty(),
                () -> assertThat(knight.findPath(C2, A3, WHITE_PAWN)).isEmpty());
    }

    @DisplayName("목적 지점이 행마법상 이동 불가능한 지역이면 예외가 발생한다.")
    @Test
    void fail_by_move_rule() {
        assertThatThrownBy(() -> knight.findPath(A1, H8, WHITE_PAWN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("행마법 상 이동할 수 없는 위치입니다.");
    }
}
