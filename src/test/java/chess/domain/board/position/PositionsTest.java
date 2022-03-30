package chess.domain.board.position;

import static chess.domain.board.File.A;
import static chess.domain.board.Rank.ONE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionsTest {
    
    @Test
    @DisplayName("Position 을 찾을 수 있다")
    void findPosition() {
        assertThat(Positions.findPositionBy(A, ONE)).isEqualTo(new Position(A, ONE));
    }

}
