package model.position;

import model.direction.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    @DisplayName("전달 받은 위치가 체스판 위에 존재하는 위치인지 판별한다.")
    void isAvailablePosition_ShouldReturnTrue_WhenAvailableFileAndRank() {
        Position position = Position.of(File.A, Rank.ONE);

        assertAll(() -> {
            assertTrue(position.isAvailablePosition(Direction.N));
            assertTrue(position.isAvailablePosition(Direction.NE));
            assertTrue(position.isAvailablePosition(Direction.E));
            assertFalse(position.isAvailablePosition(Direction.NW));
            assertFalse(position.isAvailablePosition(Direction.W));
            assertFalse(position.isAvailablePosition(Direction.SW));
            assertFalse(position.isAvailablePosition(Direction.S));
            assertFalse(position.isAvailablePosition(Direction.SE));
        });
    }
}
