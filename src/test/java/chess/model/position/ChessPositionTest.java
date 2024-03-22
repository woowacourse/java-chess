package chess.model.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessPositionTest {

    @DisplayName("다른 위치와 거리를 계산한다.")
    @Test
    void calculateMovement() {
        // given
        ChessPosition now = new ChessPosition(File.C, Rank.SIX);
        ChessPosition other = new ChessPosition(File.A, Rank.ONE);

        // when
        Movement movement = now.calculateMovement(other);

        // then
        assertThat(movement.getFileDifference()).isEqualTo(2);
        assertThat(movement.getRankDifference()).isEqualTo(5);

    }
}
