package model.piece.state;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import model.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @Test
    @DisplayName("Bishop의 현재 위치에서 이동할 수 있는 Position을 반환한다.")
    void possiblePositions_ReturnPossiblePositions_WhenCurrentPositionIsGiven() {
        Role bishop = new Bishop();
        Position initialPosition = Position.of(4, 4);
        Set<Position> positions = bishop.possiblePositions(initialPosition);

        Set<Position> expectedPositions = Set.of(Position.of(5, 5), Position.of(6, 6), Position.of(7, 7),
                Position.of(8, 8), Position.of(3, 3), Position.of(2, 2), Position.of(1, 1), Position.of(5, 3),
                Position.of(6, 2), Position.of(7, 1), Position.of(3, 5), Position.of(2, 6), Position.of(1, 7));

        assertEquals(expectedPositions, positions);
    }
}
