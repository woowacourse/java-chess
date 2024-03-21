package chess.domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.piece.Color;
import chess.domain.piece.Position;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @DisplayName("퀸을 직선으로 이동한다.")
    @Test
    void canMoveStraight() {
        // given
        final Queen queen = new Queen(Color.BLACK, new Position('d', 5));

        // when
        boolean canMove = queen.canMoveTo(new Position('d', 8));

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("퀸을 대각선으로 이동한다.")
    @Test
    void canMoveDiagonal() {
        // given
        final Queen queen = new Queen(Color.BLACK, new Position('d', 5));

        // when
        boolean canMove = queen.canMoveTo(new Position('f', 7));

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("퀸은 직선, 대각선 이외로는 이동할 수 없다.")
    @Test
    void canNotMove() {
        // given
        final Queen queen = new Queen(Color.BLACK, new Position('d', 5));

        // when
        boolean canMove = queen.canMoveTo(new Position('a', 1));

        // then
        assertThat(canMove).isFalse();
    }

    @DisplayName("도착 지점이 왼쪽일 때 위치들을 반환한다.")
    @Test
    void getRouteLeft() {
        // given
        final Queen queen = new Queen(Color.BLACK, new Position('d', 5));

        // when
        Set<Position> positions = queen.getRoute(new Position('g', 2));

        // then
        assertThat(positions).containsExactlyInAnyOrder(
                new Position('e', 4),
                new Position('f', 3)
        );
    }
}
