package chess.board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.PositionValue;

public class PositionValueTest {
    @DisplayName("PositionValue 생성 확인")
    @Test
    public void create() {
        assertDoesNotThrow(() -> new PositionValue(1));
    }

    @DisplayName("같은 PositionValue 확인")
    @Test
    void isSamePositionValue() {
        PositionValue positionValue = new PositionValue(1);
        assertTrue(positionValue.isSameAs(1));
    }

    @DisplayName("PositionValue 빼기 확인")
    @Test
    void subtractPositionValue() {
        PositionValue positionValue = new PositionValue(3);
        assertEquals(2, positionValue.subtract(new PositionValue(1)));
        assertEquals(-1, positionValue.subtract(new PositionValue(4)));
    }

    @DisplayName("PositionValue 더하기 확인")
    @Test
    void addPositionValue() {
        PositionValue positionValue = new PositionValue(3);
        assertEquals(4, positionValue.add(new PositionValue(1)));
        assertEquals(7, positionValue.add(new PositionValue(4)));
    }
}
