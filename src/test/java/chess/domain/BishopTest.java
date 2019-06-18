package chess.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BishopTest {
    @Test
    void 생성() {
        assertDoesNotThrow(() -> new Bishop(true));
    }
}
