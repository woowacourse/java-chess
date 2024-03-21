package model.piece.state;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import model.Position;
import model.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class KnightTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("Knight의 현재 위치에서 이동할 수 있는 Position을 반환한다.")
    void possiblePositions_ReturnsPossiblePositions_WhenCurrentPositionIsGiven(Color color) {
        Role knight = new Knight(color);
        Position initialPosition = Position.of(4, 4);
        Set<Position> positions = knight.possiblePositions(initialPosition);

        Set<Position> expectedPositions = Set.of(
                // NN_
                Position.of(6, 5), Position.of(6, 3),
                // SS_
                Position.of(2, 5), Position.of(2, 3),
                // EE_
                Position.of(5, 6), Position.of(3, 6),
                // WWW_
                Position.of(5, 2), Position.of(3, 2)
        );

        assertEquals(expectedPositions, positions);
    }
}
