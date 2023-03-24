package chess.domain;

import chess.domain.piece.Knight;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class KnightTest {

    private final Knight knight = Knight.instance(BLACK);

    @DisplayName("시작 지점과 목적 지점 사이의 모든 경로를 반환한다.")
    @Test
    void success_rightUp() {
        assertAll(
                () -> assertThat(knight.findPath(PositionFixtures.C2, PositionFixtures.B4, WHITE)).isEmpty(),
                () -> assertThat(knight.findPath(PositionFixtures.C2, PositionFixtures.D4, WHITE)).isEmpty(),
                () -> assertThat(knight.findPath(PositionFixtures.C2, PositionFixtures.E3, WHITE)).isEmpty(),
                () -> assertThat(knight.findPath(PositionFixtures.C2, PositionFixtures.E1, WHITE)).isEmpty(),
                () -> assertThat(knight.findPath(PositionFixtures.C3, PositionFixtures.D1, WHITE)).isEmpty(),
                () -> assertThat(knight.findPath(PositionFixtures.C3, PositionFixtures.B1, WHITE)).isEmpty(),
                () -> assertThat(knight.findPath(PositionFixtures.C2, PositionFixtures.A1, WHITE)).isEmpty(),
                () -> assertThat(knight.findPath(PositionFixtures.C2, PositionFixtures.A3, WHITE)).isEmpty());
    }

    @DisplayName("목적 지점이 행마법상 이동 불가능한 지역이면 예외가 발생한다.")
    @Test
    void fail_by_move_rule() {
        assertThatThrownBy(() -> knight.findPath(PositionFixtures.A1, PositionFixtures.H8, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("행마법상 이동 불가능한 지역입니다.");
    }

    @DisplayName("목적 지점에 아군의 기물이 있으면 예외가 발생한다.")
    @Test
    void fail_by_same_color_piece() {
        assertThatThrownBy(() -> knight.findPath(PositionFixtures.A1, PositionFixtures.C2, BLACK))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("아군의 기물이 존재하는 곳으로는 이동할 수 없습니다.");
    }
}
