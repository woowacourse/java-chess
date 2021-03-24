package chess.board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Column;

class ColumnTest {
    @DisplayName("Column 생성 확인")
    @Test
    public void create() {
        assertDoesNotThrow(() -> new Column(1));
    }

    @DisplayName("같은 Column 확인")
    @Test
    void isSameColumn() {
        Column column = new Column(1);
        assertTrue(column.isSameAs(1));
    }

    @DisplayName("Column 빼기 확인")
    @Test
    void subtractColumn() {
        Column column = new Column(3);
        assertEquals(2, column.subtract(new Column(1)));
        assertEquals(-1, column.subtract(new Column(4)));
    }

    @DisplayName("Column 더하기 확인")
    @Test
    void addColumn() {
        Column column = new Column(3);
        assertEquals(4, column.add(new Column(1)));
        assertEquals(7, column.add(new Column(4)));
    }

}