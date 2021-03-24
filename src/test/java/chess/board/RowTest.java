package chess.board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Row;

class RowTest {
    @DisplayName("Row 생성 확인")
    @Test
    public void create() {
        assertDoesNotThrow(() -> new Row(1));
    }

    @DisplayName("같은 Row 확인")
    @Test
    void isSameRow() {
        Row row = new Row(1);
        assertTrue(row.isSameAs(1));
    }

    @DisplayName("Row 빼기 확인")
    @Test
    void subtractRow() {
        Row row = new Row(3);
        assertEquals(2, row.subtract(new Row(1)));
        assertEquals(-1, row.subtract(new Row(4)));
    }

    @DisplayName("Row 더하기 확인")
    @Test
    void addRow() {
        Row row = new Row(3);
        assertEquals(4, row.add(new Row(1)));
        assertEquals(7, row.add(new Row(4)));
    }
}