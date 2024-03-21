package model.piece.state;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import model.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @Test
    @DisplayName("Rook의 현재 위치에서 이동할 수 있는 Position을 반환한다.")
    void possiblePositions_ReturnPossiblePositions_WhenCurrentPositionIsGiven() {
        Role rook = new Rook();
        Position initialPosition = Position.of(4, 4);
        Set<Position> positions = rook.possiblePositions(initialPosition);

        Set<Position> expectedPositions = Set.of(
                Position.of(4, 5), Position.of(4, 6), Position.of(4, 7), Position.of(4, 8),
                Position.of(4, 3), Position.of(4, 2), Position.of(4, 1),
                Position.of(5, 4), Position.of(6, 4), Position.of(7, 4), Position.of(8, 4),
                Position.of(3, 4), Position.of(2, 4), Position.of(1, 4)
        );

        assertEquals(expectedPositions, positions);
    }
}
