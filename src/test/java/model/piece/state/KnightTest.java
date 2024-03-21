package model.piece.state;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import model.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    @Test
    @DisplayName("Knight의 현재 위치에서 이동할 수 있는 Position을 반환한다.")
    void possiblePositions_ReturnsPossiblePositions_WhenCurrentPositionIsGiven() {
        Role knight = new Knight();
        Position initialPosition = Position.of(4, 4);
        Set<Position> positions = knight.possiblePositions(initialPosition);

        Set<Position> expectedPositions = Set.of(
                Position.of(6, 5), Position.of(6, 3),
                Position.of(2, 5), Position.of(2, 3),
                Position.of(5, 6), Position.of(3, 6),
                Position.of(5, 2), Position.of(3, 2)
        );

        assertEquals(expectedPositions, positions);
    }
}
