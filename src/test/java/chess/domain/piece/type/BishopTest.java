package chess.domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.piece.Color;
import chess.domain.piece.Position;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @DisplayName("비숍을 대각선으로 이동한다.")
    @Test
    void canMove() {
        // given
        final Bishop bishop = new Bishop(Color.BLACK, new Position('d', 5));

        // when
        boolean canMove = bishop.canMoveTo(new Position('e', 4));

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("비숍은 대각선 이외로는 이동할 수 없다.")
    @Test
    void canNotMove() {
        // given
        final Bishop bishop = new Bishop(Color.BLACK, new Position('d', 5));

        // when
        boolean canMove = bishop.canMoveTo(new Position('d', 6)); // 대각선 위

        // then
        assertThat(canMove).isFalse();
    }

    @DisplayName("도착 지점이 오른쪽 위 대각선일 때 위치들을 반환한다.")
    @Test
    void getRouteRightUp() {
        // given
        final Bishop bishop = new Bishop(Color.BLACK, new Position('a', 2));

        // when
        Set<Position> positions = bishop.getRoute(new Position('d', 5));

        // then
        assertThat(positions).containsExactlyInAnyOrder(
                new Position('b', 3),
                new Position('c', 4)
        );
    }
    @DisplayName("도착 지점이 오른쪽 아래 대각선일 때 위치들을 반환한다.")
    @Test
    void getRouteRightDown() {
        // given
        final Bishop bishop = new Bishop(Color.BLACK, new Position('c', 4));

        // when
        Set<Position> positions = bishop.getRoute(new Position('f', 1));

        // then
        assertThat(positions).containsExactlyInAnyOrder(
                new Position('d', 3),
                new Position('e', 2)
        );
    }
    @DisplayName("도착 지점이 왼쪽 위 대각선일 때 위치들을 반환한다.")
    @Test
    void getRouteLeftUp() {
        // given
        final Bishop bishop = new Bishop(Color.BLACK, new Position('c', 4));

        // when
        Set<Position> positions = bishop.getRoute(new Position('a', 6));

        // then
        assertThat(positions).containsExactlyInAnyOrder(
                new Position('b', 5)
        );
    }
    @DisplayName("도착 지점이 왼쪽 아래 대각선일 때 위치들을 반환한다.")
    @Test
    void getRouteLeftDown() {
        // given
        final Bishop bishop = new Bishop(Color.BLACK, new Position('d', 6));

        // when
        Set<Position> positions = bishop.getRoute(new Position('a', 3));

        // then
        assertThat(positions).containsExactlyInAnyOrder(
                new Position('c', 5),
                new Position('b', 4)
        );
    }
}
