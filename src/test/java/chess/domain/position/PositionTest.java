package chess.domain.position;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @DisplayName("Position을 생성한다.")
    @Test
    void position() {
        Position actual = Position.of(Column.A, Row.ONE);
        assertEquals(actual, Position.of(Column.A, Row.ONE));
        assertEquals(actual, Position.of("a1"));
    }
}