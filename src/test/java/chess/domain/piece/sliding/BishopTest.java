package chess.domain.piece.sliding;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.color.Color;
import chess.domain.piece.Position;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @Test
    @DisplayName("(1, 1) -> (2 + ?, 2 + ?)")
    void findPathToDiagonal() {
        Bishop bishop = new Bishop(new Position(1, 1), Color.WHITE);
        Set<Position> movablePositions = bishop.findPathTo(new Position(4, 4));
        assertThat(movablePositions)
                .containsExactlyInAnyOrder(new Position(2, 2), new Position(3, 3));
    }

    @Test
    @DisplayName("(1, 1) -> (2, 1) 예외 발생")
    void findPathToInvalidDestination() {
        Bishop bishop = new Bishop(new Position(1, 1), Color.WHITE);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> bishop.findPathTo(new Position(2, 1)))
                .withMessage("이동할 수 없습니다.");
    }
}
