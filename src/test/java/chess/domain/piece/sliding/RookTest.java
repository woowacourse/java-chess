package chess.domain.piece.sliding;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.color.Color;
import chess.domain.piece.Position;
import chess.domain.piece.sliding.Rook;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @Test
    @DisplayName("(1, 1) -> (?, 1)")
    void findMovablePositionsRight() {
        Rook rook = new Rook(new Position(1, 1), Color.WHITE);
        Set<Position> movablePositions = rook.findMovablePositions(new Position(4, 1));
        assertThat(movablePositions)
                .containsExactlyInAnyOrder(new Position(2, 1), new Position(3, 1));
    }

    @Test
    @DisplayName("(1, 1) -> (1, ?)")
    void findMovablePositionsUp() {
        Rook rook = new Rook(new Position(1, 1), Color.WHITE);
        Set<Position> movablePositions = rook.findMovablePositions(new Position(1, 4));
        assertThat(movablePositions)
                .containsExactlyInAnyOrder(new Position(1, 2), new Position(1, 3));
    }

    @Test
    @DisplayName("(1, 1) -> (2, 2) 예외 발생")
    void findMovablePositionsByInvalidDestination() {
        Rook rook = new Rook(new Position(1, 1), Color.WHITE);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> rook.findMovablePositions(new Position(2, 2)))
                .withMessage("이동할 수 없습니다.");
    }
}
