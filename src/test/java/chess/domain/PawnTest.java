package chess.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PawnTest {
    @Test
    void 생성() {
        assertDoesNotThrow(() -> new Pawn(true));
    }
}
