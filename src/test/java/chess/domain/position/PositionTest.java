package chess.domain.position;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @DisplayName("Position을 생성한다.")
    @Test
    void position(){
        Position actual = Position.ofColumnAndRow(Column.A,Row.ONE);
        assertEquals(actual, Position.ofColumnAndRow(Column.A,Row.ONE));
        assertEquals(actual, Position.ofName("a1"));
    }
}