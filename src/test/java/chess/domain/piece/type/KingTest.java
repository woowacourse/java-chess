package chess.domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @DisplayName("킹을 직선으로 한칸 이동한다.")
    @Test
    void canMoveStraight() {
        // given
        final King king = new King(Color.BLACK, new Position('d', 5));

        // when
        boolean canMove = king.canMoveTo(new Position('d', 6));

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("킹을 대각선으로 한칸 이동한다.")
    @Test
    void canMoveDiagonal() {
        // given
        final King king = new King(Color.BLACK, new Position('d', 5));

        // when
        boolean canMove = king.canMoveTo(new Position('e', 6));

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("킹은 직선, 대각선 한칸 초과로는 이동할 수 없다.")
    @Test
    void canNotMove() {
        // given
        final King king = new King(Color.BLACK, new Position('d', 5));

        // when
        boolean canMove = king.canMoveTo(new Position('a', 1));

        // then
        assertThat(canMove).isFalse();
    }
}
