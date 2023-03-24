package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.King;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KingTest {

    private final King king = King.create(Color.BLACK);

    @DisplayName("시작 지점과 목적 지점 사이의 모든 경로를 반환한다. - 우상향으로 이동하는 경우")
    @Test
    void success_rightUp() {
        final List<Position> path = king.findPath(PositionFixtures.A1, PositionFixtures.B2, Color.WHITE);

        assertThat(path).isEmpty();
    }

    @DisplayName("시작 지점과 목적 지점 사이의 모든 경로를 반환한다. - 우로 이동하는 경우")
    @Test
    void success_right() {
        final List<Position> path = king.findPath(PositionFixtures.A1, PositionFixtures.A2, Color.WHITE);

        assertThat(path).isEmpty();
    }

    @DisplayName("킹은 목적 지점과 방향이 같더라도, 두 칸 이상 떨어져 있다면 예외가 발생한다.")
    @Test
    void fail_by_move_length() {
        assertThatThrownBy(() -> king.findPath(PositionFixtures.A1, PositionFixtures.A3, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("한 칸만 이동 가능합니다.");
    }

    @DisplayName("목적 지점이 행마법상 이동 불가능한 지역이면 예외가 발생한다.")
    @Test
    void fail_by_move_rule() {
        assertThatThrownBy(() -> king.findPath(PositionFixtures.A1, PositionFixtures.C2, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("행마법상 이동 불가능한 지역입니다.");
    }

    @DisplayName("목적 지점에 아군의 기물이 있으면 예외가 발생한다.")
    @Test
    void fail_by_same_color_piece() {
        assertThatThrownBy(() -> king.findPath(PositionFixtures.A1, PositionFixtures.H8, Color.BLACK))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("아군의 기물이 존재하는 곳으로는 이동할 수 없습니다.");
    }
}
