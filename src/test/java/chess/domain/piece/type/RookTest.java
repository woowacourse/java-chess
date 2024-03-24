package chess.domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Movement;
import chess.domain.piece.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @DisplayName("룩은 직선 이외로는 이동할 수 없다.")
    @Test
    void canNotMove() {
        // given
        final Rook rook = new Rook(Color.BLACK);
        final Movement movement = new Movement(new Position(File.D, Rank.FIVE), new Position(File.E, Rank.FOUR)); // 대각선 위로 이동하는 움직임

        // when && then
        assertThat(rook.canMove(movement)).isFalse();
    }

    @DisplayName("도착 지점이 위쪽일 때의 위치들을 반환한다.")
    @Test
    void getRouteUp() {
        // given
        final Rook rook = new Rook(Color.BLACK);
        final Movement movement = new Movement(new Position(File.A, Rank.FIVE), new Position(File.A, Rank.EIGHT));

        // when
        final Set<Position> positions = rook.getRoute(movement);

        // then
        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.A, Rank.SIX),
                new Position(File.A, Rank.SEVEN)
        );
    }

    @DisplayName("도착 지점이 아래쪽일 때 위치들을 반환한다.")
    @Test
    void getRouteDown() {
        // given
        final Rook rook = new Rook(Color.BLACK);
        final Movement movement = new Movement(new Position(File.A, Rank.FIVE), new Position(File.A, Rank.TWO));

        // when
        final Set<Position> positions = rook.getRoute(movement);

        // then
        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.A, Rank.FOUR),
                new Position(File.A, Rank.THREE)
        );
    }

    @DisplayName("도착 지점이 오른쪽일 때 위치들을 반환한다.")
    @Test
    void getRouteRight() {
        // given
        final Rook rook = new Rook(Color.BLACK);
        final Movement movement = new Movement(new Position(File.A, Rank.FIVE), new Position(File.D, Rank.FIVE));

        // when
        final Set<Position> positions = rook.getRoute(movement);

        // then
        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.B, Rank.FIVE),
                new Position(File.C, Rank.FIVE)
        );
    }

    @DisplayName("도착 지점이 왼쪽일 때 위치들을 반환한다.")
    @Test
    void getRouteLeft() {
        // given
        final Rook rook = new Rook(Color.BLACK);
        final Movement movement = new Movement(new Position(File.D, Rank.FIVE), new Position(File.A, Rank.FIVE));

        // when
        final Set<Position> positions = rook.getRoute(movement);

        // then
        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.C, Rank.FIVE),
                new Position(File.B, Rank.FIVE)
        );
    }
}
