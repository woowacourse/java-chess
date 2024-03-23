package chess.domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.File;
import chess.domain.piece.Position;
import chess.domain.piece.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @DisplayName("킹을 직선으로 한칸 이동한다.")
    @Test
    void canMoveStraight() {
        // given
        final King king = new King(Color.WHITE);

        // when
        final boolean canMove = king.canMoveTo(new Position(File.D, Rank.FIVE), new Position(File.D, Rank.SIX));

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("킹을 대각선으로 한칸 이동한다.")
    @Test
    void canMoveDiagonal() {
        // given
        final King king = new King(Color.WHITE);

        // when
        final boolean canMove = king.canMoveTo(new Position(File.D, Rank.FIVE), new Position(File.E, Rank.SIX));

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("킹은 직선, 대각선 한칸 초과로는 이동할 수 없다.")
    @Test
    void canNotMove() {
        // given
        final King king = new King(Color.BLACK);

        // when
        final boolean canMove = king.canMoveTo(new Position(File.D, Rank.FIVE), new Position(File.A, Rank.ONE));

        // then
        assertThat(canMove).isFalse();
    }
}
