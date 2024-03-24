package chess.domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.File;
import chess.domain.piece.Position;
import chess.domain.piece.Rank;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @DisplayName("비숍을 대각선으로 이동한다.")
    @Test
    void canMoveDiagonal() {
        // given
        final Bishop bishop = new Bishop(Color.BLACK);

        // when
        boolean canMove = bishop.canMoveTo(new Position(File.D, Rank.FIVE), new Position(File.E, Rank.FOUR));

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("비숍은 대각선 이외로는 이동할 수 없다.")
    @Test
    void canNotMove() {
        // given
        final Bishop bishop = new Bishop(Color.BLACK);

        // when
        boolean canMove = bishop.canMoveTo(new Position(File.D, Rank.FIVE), new Position(File.D, Rank.SIX)); // 바로 위

        // then
        assertThat(canMove).isFalse();
    }

    @DisplayName("도착 지점이 오른쪽 위 대각선일 때 위치들을 반환한다.")
    @Test
    void getRouteRightUp() {
        // given
        final Bishop bishop = new Bishop(Color.WHITE);

        // when
        final Set<Position> positions = bishop.getRoute(new Position(File.A, Rank.TWO), new Position(File.D, Rank.FIVE));

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

        // when
        final Set<Position> positions = bishop.getRoute(new Position(File.C, Rank.FOUR), new Position(File.F, Rank.ONE));

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

        // when
        final Set<Position> positions = bishop.getRoute(new Position(File.C, Rank.FOUR), new Position(File.A, Rank.SIX));

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

        // when
        final Set<Position> positions = bishop.getRoute(new Position(File.D, Rank.SIX), new Position(File.A, Rank.THREE));

        // then
        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.C, Rank.FIVE),
                new Position(File.B, Rank.FOUR)
        );
    }
}
