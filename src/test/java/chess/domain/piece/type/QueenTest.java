package chess.domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.File;
import chess.domain.piece.Position;
import chess.domain.piece.Rank;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @DisplayName("퀸을 직선으로 이동한다.")
    @Test
    void canMoveStraight() {
        // given
        final Queen queen = new Queen(Color.BLACK);

        // when
        final boolean canMove = queen.canMoveTo(new Position(File.D, Rank.FIVE), new Position(File.D, Rank.EIGHT));

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("퀸을 대각선으로 이동한다.")
    @Test
    void canMoveDiagonal() {
        // given
        final Queen queen = new Queen(Color.BLACK);

        // when
        final boolean canMove = queen.canMoveTo(new Position(File.D, Rank.FIVE), new Position(File.F, Rank.SEVEN));

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("퀸은 직선, 대각선 이외로는 이동할 수 없다.")
    @Test
    void canNotMove() {
        // given
        final Queen queen = new Queen(Color.BLACK);

        // when
        final boolean canMove = queen.canMoveTo(new Position(File.D, Rank.FIVE), new Position(File.A, Rank.ONE));

        // then
        assertThat(canMove).isFalse();
    }

    @DisplayName("도착 지점이 왼쪽일 때 위치들을 반환한다.")
    @Test
    void getRouteLeft() {
        // given
        final Queen queen = new Queen(Color.BLACK);

        // when
        final Set<Position> positions = queen.getRoute(new Position(File.D, Rank.FIVE), new Position(File.G, Rank.TWO));

        // then
        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.E, Rank.FOUR),
                new Position(File.F, Rank.THREE)
        );
    }
}
