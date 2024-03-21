package model.piece.state;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import model.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @Test
    @DisplayName("King의 현재 위치에서 이동할 수 있는 Position을 반환한다.")
    void possiblePositions_ReturnsPossiblePositions_WhenCurrentPositionIsGiven() {
        Role king = new King();
        Position initialPosition = Position.of(4, 4);
        Set<Position> positions = king.possiblePositions(initialPosition);

        Set<Position> expectedPositions = Set.of(
                Position.of(3, 3), Position.of(3, 4), Position.of(3, 5),
                Position.of(4, 3), Position.of(4, 5),
                Position.of(5, 3), Position.of(5, 4), Position.of(5, 5)
        );

        assertEquals(expectedPositions, positions);
    }
}
