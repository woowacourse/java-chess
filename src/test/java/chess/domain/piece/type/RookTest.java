package chess.domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.Position;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @DisplayName("룩을 직선으로 이동한다.")
    @Test
    void canMove() {
        // given
        final Rook rook = new Rook(Color.BLACK, new Position('d', 5));

        // when
        boolean canMove = rook.canMoveTo(new Position('d', 6)); // 상

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("룩은 직선 이외로는 이동할 수 없다.") // TODO: 대각선+직선 이동 케이스 추가
    @Test
    void canNotMove() {
        // given
        final Rook rook = new Rook(Color.BLACK, new Position('d', 5));

        // when
        boolean canMove = rook.canMoveTo(new Position('e', 4)); // 대각선 위

        // then
        assertThat(canMove).isFalse();
    }

    @DisplayName("도착 지점이 위쪽일 때의 위치들을 반환한다.")
    @Test
    void getRouteUp() {
        // given
        final Rook rook = new Rook(Color.BLACK, new Position('a', 5));

        // when
        final Set<Position> positions = rook.getRoute(new Position('a', 8));

        // then
        assertThat(positions).containsExactlyInAnyOrder(
                new Position('a', 6),
                new Position('a', 7)
        );
    }

    @DisplayName("도착 지점이 아래쪽일 때 위치들을 반환한다.")
    @Test
    void getRouteDown() {
        // given
        final Rook rook = new Rook(Color.BLACK, new Position('a', 5));

        // when
        final Set<Position> positions = rook.getRoute(new Position('a', 2));

        // then
        assertThat(positions).containsExactlyInAnyOrder(
                new Position('a', 4),
                new Position('a', 3)
        );
    }

    @DisplayName("도착 지점이 오른쪽일 때 위치들을 반환한다.")
    @Test
    void getRouteRight() {
        // given
        final Rook rook = new Rook(Color.BLACK, new Position('a', 5));

        // when
        Set<Position> positions = rook.getRoute(new Position('d', 5));

        // then
        assertThat(positions).containsExactlyInAnyOrder(
                new Position('b', 5),
                new Position('c', 5)
        );
    }

    @DisplayName("도착 지점이 왼쪽일 때 위치들을 반환한다.")
    @Test
    void getRouteLeft() {
        // given
        final Rook rook = new Rook(Color.BLACK, new Position('d', 5));

        // when
        Set<Position> positions = rook.getRoute(new Position('a', 5));

        // then
        assertThat(positions).containsExactlyInAnyOrder(
                new Position('b', 5),
                new Position('c', 5)
        );
    }
}
