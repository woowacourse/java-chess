package chess.domain;

import chess.domain.game.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BishopTest {

    private final Bishop bishop = Bishop.create(Color.BLACK);

    @DisplayName("시작 지점과 목적 지점 사이의 모든 경로를 반환한다.")
    @Test
    void success() {
        final List<Position> path = bishop.findPath(PositionFixtures.A1, PositionFixtures.H8, Color.WHITE);

        assertThat(path).containsExactly(PositionFixtures.B2, PositionFixtures.C3, PositionFixtures.D4, PositionFixtures.E5, PositionFixtures.F6, PositionFixtures.G7);
    }

    @DisplayName("목적 지점이 행마법상 이동 불가능한 지역이면 예외가 발생한다.")
    @Test
    void fail_by_move_rule() {
        assertThatThrownBy(() -> bishop.findPath(PositionFixtures.A1, PositionFixtures.C2, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("행마법상 이동 불가능한 지역입니다.");
    }

    @DisplayName("목적 지점에 아군의 기물이 있으면 예외가 발생한다.")
    @Test
    void fail_by_same_color_piece() {
        assertThatThrownBy(() -> bishop.findPath(PositionFixtures.A1, PositionFixtures.H8, Color.BLACK))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("아군의 기물이 존재하는 곳으로는 이동할 수 없습니다.");
    }
}
