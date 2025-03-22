package chess.piece;

import static chess.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

import chess.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @DisplayName("룩은 세로로 움직일 수 잇다.")
    @Test
    void Rook_canMove_Straight_Row() {
        // given
        Rook rook = new Rook();

        // when
        boolean canMove = rook.availablePath(A1, A8);

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("룩은 가로로 움직일 수 잇다.")
    @Test
    void Rook_canMove_Straight_Column() {
        // given
        Rook rook = new Rook();

        // when
        boolean canMove = rook.availablePath(A1, H1);

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("룩은 직선이 아니면 움직일 수 없다.")
    @Test
    void Rook_cannotMove_when_notStraight() {
        // given
        Rook rook = new Rook();

        // when
        boolean canMove = rook.availablePath(A1, B2);

        // then
        assertThat(canMove).isFalse();
    }

    @DisplayName("룩 이동 경로의 위치를 모두 반환한다.")
    @Test
    void Rook_findAllRoute() {
        // given
        Rook rook = new Rook();

        // when
        List<Position> routes = rook.findAllRouteToTarget(A1, A4);

        // then
        assertThat(routes).hasSize(3);
        assertThat(routes.get(0)).isEqualTo(A2);
        assertThat(routes.get(1)).isEqualTo(A3);
        assertThat(routes.get(2)).isEqualTo(A4);
    }

}