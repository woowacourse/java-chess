package chess.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class QueenTest {
    @Test
    void 생성() {
        assertDoesNotThrow(() -> new Queen(true));
    }
}
