package chess.domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.File;
import chess.domain.piece.Position;
import chess.domain.piece.Rank;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @DisplayName("룩을 직선으로 이동한다.")
    @Test
    void canMove() {
        // given
        final Rook rook = new Rook(Color.BLACK);

        // when
        final boolean canMove = rook.canMoveTo(new Position(File.D, Rank.FIVE), new Position(File.D, Rank.SIX)); // 상

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("룩은 직선 이외로는 이동할 수 없다.") // TODO: 대각선+직선 이동 케이스 추가
    @Test
    void canNotMove() {
        // given
        final Rook rook = new Rook(Color.BLACK);

        // when
        final boolean canMove = rook.canMoveTo(new Position(File.D, Rank.FIVE),
                new Position(File.E, Rank.FOUR)); // 대각선 위

        // then
        assertThat(canMove).isFalse();
    }

    @DisplayName("도착 지점이 위쪽일 때의 위치들을 반환한다.")
    @Test
    void getRouteUp() {
        // given
        final Rook rook = new Rook(Color.BLACK);

        // when
        final Set<Position> positions = rook.getRoute(new Position(File.A, Rank.FIVE),
                new Position(File.A, Rank.EIGHT));

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

        // when
        final Set<Position> positions = rook.getRoute(new Position(File.A, Rank.FIVE), new Position(File.A, Rank.TWO));

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

        // when
        final Set<Position> positions = rook.getRoute(new Position(File.A, Rank.FIVE), new Position(File.D, Rank.FIVE));

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

        // when
        final Set<Position> positions = rook.getRoute(new Position(File.D, Rank.FIVE), new Position(File.A, Rank.FIVE));

        // then
        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.B, Rank.FIVE),
                new Position(File.C, Rank.FIVE)
        );
    }
}
