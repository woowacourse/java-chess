package techcourse.fp.chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static techcourse.fp.chess.domain.Color.BLACK;
import static techcourse.fp.chess.domain.Color.WHITE;
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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    private final Knight knight = new Knight(BLACK);

    @DisplayName("시작 지점과 목적 지점 사이의 모든 경로를 반환한다.")
    @Test
    void success_rightUp() {
        assertAll(
                () -> assertThat(knight.findPath(C2, B4, WHITE)).isEmpty(),
                () -> assertThat(knight.findPath(C2, D4, WHITE)).isEmpty(),
                () -> assertThat(knight.findPath(C2, E3, WHITE)).isEmpty(),
                () -> assertThat(knight.findPath(C2, E1, WHITE)).isEmpty(),
                () -> assertThat(knight.findPath(C3, D1, WHITE)).isEmpty(),
                () -> assertThat(knight.findPath(C3, B1, WHITE)).isEmpty(),
                () -> assertThat(knight.findPath(C2, A1, WHITE)).isEmpty(),
                () -> assertThat(knight.findPath(C2, A3, WHITE)).isEmpty());
    }

    @DisplayName("목적 지점이 행마법상 이동 불가능한 지역이면 예외가 발생한다.")
    @Test
    void fail_by_move_rule() {
        assertThatThrownBy(() -> knight.findPath(A1, H8, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("행마법상 이동 불가능한 지역입니다.");
    }

    @DisplayName("목적 지점에 아군의 기물이 있으면 예외가 발생한다.")
    @Test
    void fail_by_same_color_piece() {
        assertThatThrownBy(() -> knight.findPath(A1, C2, BLACK))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("아군의 기물이 존재하는 곳으로는 이동할 수 없습니다.");
    }
}
