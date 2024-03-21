package model.piece.state;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import model.Position;
import model.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class KingTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("King의 현재 위치에서 이동할 수 있는 Position을 반환한다.")
    void possiblePositions_ReturnsPossiblePositions_WhenCurrentPositionIsGiven(Color color) {
        Role king = new King(color);
        Position initialPosition = Position.of(4, 4);
        Set<Position> positions = king.possiblePositions(initialPosition);

        Set<Position> expectedPositions = Set.of(
                // South-West
                Position.of(3, 3),
                // West
                Position.of(3, 4),
                // North-West
                Position.of(3, 5),
                // South
                Position.of(4, 3),
                // North
                Position.of(4, 5),
                // North-East
                Position.of(5, 3),
                // East
                Position.of(5, 4),
                // South-East
                Position.of(5, 5)
        );

        assertEquals(expectedPositions, positions);
    }
}
