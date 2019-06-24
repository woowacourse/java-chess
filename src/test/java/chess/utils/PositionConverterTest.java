package chess.utils;

import chess.domain.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PositionConverterTest {

    @Test
    public void 두자리를_제대로_반환하는지_확인() {
        Position expected = Position.of("2", "a");
        Position actual = PositionConverter.convert("a2");
        assertEquals(expected, actual);
    }

    @Test
    public void 빈칸_변환할때_예외발생() {
        assertThrows(IllegalArgumentException.class, () ->
                PositionConverter.convert("  ")
        );
    }

    @Test
    public void 세자리를_변환할때_예외발생() {
        assertThrows(IllegalArgumentException.class, () ->
                PositionConverter.convert("a23")
        );
    }

    @Test
    public void 한자리를_변환할때_예외발생() {
        assertThrows(IllegalArgumentException.class, () ->
                PositionConverter.convert("a")
        );
    }

}