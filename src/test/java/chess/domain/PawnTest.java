package chess.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {
    
    @Test
    @DisplayName("Pawn 은 위치를 가진다.")
    void createPawnSuccess() {
        assertDoesNotThrow(() -> new Pawn(new Position(1, 2)));
    }

}
