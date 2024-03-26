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

class BishopTest {

    @DisplayName("비숍은 대각선 이외로는 이동할 수 없다.")
    @Test
    void canNotMove() {
        // given
        final Bishop bishop = new Bishop(Color.BLACK);
        final Movement movement = new Movement(new Position(File.D, Rank.FIVE), new Position(File.D, Rank.SIX)); // 대각선 위로 이동하는 움직임

        // when && then
        assertThat(bishop.canMove(movement)).isFalse();
    }

    @DisplayName("도착 지점이 오른쪽 위 대각선일 때 위치들을 반환한다.")
    @Test
    void getRouteRightUp() {
        // given
        final Bishop bishop = new Bishop(Color.WHITE);
        final Movement movement = new Movement(new Position(File.A, Rank.TWO), new Position(File.D, Rank.FIVE));

        // when
        final Set<Position> positions = bishop.getRoute(movement);

        // then
        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.B, Rank.THREE),
                new Position(File.C, Rank.FOUR)
        );
    }

    @DisplayName("도착 지점이 오른쪽 아래 대각선일 때 위치들을 반환한다.")
    @Test
    void getRouteRightDown() {
        // given
        final Bishop bishop = new Bishop(Color.BLACK);
        final Movement movement = new Movement(new Position(File.C, Rank.FOUR), new Position(File.F, Rank.ONE));

        // when
        final Set<Position> positions = bishop.getRoute(movement);

        // then
        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.D, Rank.THREE),
                new Position(File.E, Rank.TWO)
        );
    }

    @DisplayName("도착 지점이 왼쪽 위 대각선일 때 위치들을 반환한다.")
    @Test
    void getRouteLeftUp() {
        // given
        final Bishop bishop = new Bishop(Color.BLACK);
        final Movement movement = new Movement(new Position(File.C, Rank.FOUR), new Position(File.A, Rank.SIX));

        // when
        final Set<Position> positions = bishop.getRoute(movement);

        // then
        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.B, Rank.FIVE)
        );
    }

    @DisplayName("도착 지점이 왼쪽 아래 대각선일 때 위치들을 반환한다.")
    @Test
    void getRouteLeftDown() {
        // given
        final Bishop bishop = new Bishop(Color.BLACK);
        final Movement movement = new Movement(new Position(File.D, Rank.SIX), new Position(File.A, Rank.THREE));

        // when
        final Set<Position> positions = bishop.getRoute(movement);

        // then
        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.C, Rank.FIVE),
                new Position(File.B, Rank.FOUR)
        );
    }
}
