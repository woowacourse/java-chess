package chess.domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Movement;
import chess.domain.piece.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @DisplayName("킹은 직선, 대각선 한칸 초과로는 이동할 수 없다.")
    @Test
    void canNotMove() {
        // given
        final King king = new King(Color.BLACK);
        final Movement movement = new Movement(new Position(File.D, Rank.FIVE), new Position(File.A, Rank.ONE));

        // when && then
        assertThat(king.canMove(movement)).isFalse();
    }
}
