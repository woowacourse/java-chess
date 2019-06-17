package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChessPointTest {
    @Test
    void of_() {
        assertThat(ChessPoint.of(1, 1)).isEqualTo(ChessPoint.of(1, 1));
    }

    @Test
    void of_row_왼쪽으로벗어남() {
        assertThrows(IllegalArgumentException.class, () -> ChessPoint.of(ChessPoint.START - 1, 1));
    }

    @Test
    void of_row_오른쪽으로벗어남() {
        assertThrows(IllegalArgumentException.class, () -> ChessPoint.of(ChessPoint.END + 1, 1));
    }

    @Test
    void of_column_아래쪽으로벗어남() {
        assertThrows(IllegalArgumentException.class, () -> ChessPoint.of(1, ChessPoint.START - 1));
    }

    @Test
    void of_column_위쪽으로벗어남() {
        assertThrows(IllegalArgumentException.class, () -> ChessPoint.of(1, ChessPoint.END + 1));
    }

    @Test
    void of_캐시여부() {
        assertThat(ChessPoint.of(1, 1) == ChessPoint.of(1, 1)).isTrue();
    }
}