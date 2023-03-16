package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {
    Pawn pawn = new Pawn(Color.BLACK);

    @ParameterizedTest
    @CsvSource({"3,0"})
    void canMove_fail(int fileInterval, int rankInterval) {
        assertThrows(IllegalArgumentException.class,
                () -> pawn.validateMovement(fileInterval, rankInterval));
    }

    @ParameterizedTest
    @CsvSource({"1,0", "1,1"})
    void canMove_success(int fileInterval, int rankInterval) {
        assertDoesNotThrow(() -> pawn.validateMovement(fileInterval, rankInterval));
    }
}
