package model.piece.state;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import model.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @Test
    @DisplayName("Queen의 현재 위치에서 이동할 수 있는 Position을 반환한다.")
    void possiblePositions_ReturnsPossiblePositions_WhenCurrentPositionIsGiven() {
        Role queen = new Queen();
        Position initialPosition = Position.of(4, 4);
        Set<Position> positions = queen.possiblePositions(initialPosition);

        Set<Position> expectedPositions = Set.of(
                // North
                Position.of(5, 4), Position.of(6, 4), Position.of(7, 4), Position.of(8, 4),
                // South
                Position.of(3, 4), Position.of(2, 4), Position.of(1, 4),
                // East
                Position.of(4, 5), Position.of(4, 6), Position.of(4, 7), Position.of(4, 8),
                // West
                Position.of(4, 3), Position.of(4, 2), Position.of(4, 1),
                // North-East
                Position.of(5, 5), Position.of(6, 6), Position.of(7, 7), Position.of(8, 8),
                // South-East
                Position.of(3, 3), Position.of(2, 2), Position.of(1, 1),
                // North-West
                Position.of(5, 3), Position.of(6, 2), Position.of(7, 1),
                // South-West
                Position.of(3, 5), Position.of(2, 6), Position.of(1, 7)
        );

        assertEquals(expectedPositions, positions);
    }
}
