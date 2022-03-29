package chess.domain.board;

import static chess.domain.board.File.A;
import static chess.domain.board.Rank.ONE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionsTest {
    
    @Test
    @DisplayName("Position 을 찾을 수 있다")
    void findPosition() {
        Position position = new Position(A, ONE);
        Position findPosition = Positions.findPosition(A, ONE);

        assertThat(position).isEqualTo(findPosition);
    }

}
