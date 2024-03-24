package chess.domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Movement;
import chess.domain.piece.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    @DisplayName("나이트는 이동전략 이외로는 이동할 수 없다.")
    @Test
    void canNotMove() {
        // given
        final Knight knight = new Knight(Color.BLACK);
        final Movement movement = new Movement(new Position(File.D, Rank.FIVE), new Position(File.F, Rank.ONE)); // 유효하지 않은 이동 전략

        // when && then
        assertThat(knight.canMove(movement)).isFalse();
    }

}
