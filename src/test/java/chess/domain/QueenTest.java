package chess.domain;

import chess.domain.game.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Queen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QueenTest {

    private final Queen queen = Queen.create(Color.BLACK);

    @DisplayName("시작 지점과 목적 지점 사이의 모든 경로를 반환한다. - 우상향으로 이동하는 경우")
    @Test
    void success_rightUp() {
        final List<Position> path = queen.findPath(PositionFixtures.A1, PositionFixtures.H8, Color.WHITE);

        assertThat(path).containsExactly(PositionFixtures.B2, PositionFixtures.C3, PositionFixtures.D4, PositionFixtures.E5, PositionFixtures.F6, PositionFixtures.G7);
    }

    @DisplayName("시작 지점과 목적 지점 사이의 모든 경로를 반환한다. - 우로 이동하는 경우")
    @Test
    void success_right() {
        final List<Position> path = queen.findPath(PositionFixtures.A1, PositionFixtures.A8, Color.WHITE);

        assertThat(path).containsExactly(PositionFixtures.A2, PositionFixtures.A3, PositionFixtures.A4, PositionFixtures.A5, PositionFixtures.A6, PositionFixtures.A7);
    }

    @DisplayName("목적 지점이 행마법상 이동 불가능한 지역이면 예외가 발생한다.")
    @Test
    void fail_by_move_rule() {
        assertThatThrownBy(() -> queen.findPath(PositionFixtures.A1, PositionFixtures.C2, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("행마법상 이동 불가능한 지역입니다.");
    }

    @DisplayName("목적 지점에 아군의 기물이 있으면 예외가 발생한다.")
    @Test
    void fail_by_same_color_piece() {
        assertThatThrownBy(() -> queen.findPath(PositionFixtures.A1, PositionFixtures.H8, Color.BLACK))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("아군의 기물이 존재하는 곳으로는 이동할 수 없습니다.");
    }
}
