package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {

    Bishop bishop = new Bishop(Color.BLACK);

    @ParameterizedTest
    @CsvSource({"1,2"})
    void canMove_fail(int fileInterval, int rankInterval) {
        assertThrows(IllegalArgumentException.class,
                () -> bishop.validateMovement(fileInterval, rankInterval));
    }

    @ParameterizedTest
    @CsvSource({"2,2"})
    void canMove_success(int fileInterval, int rankInterval) {
        assertDoesNotThrow(() -> bishop.validateMovement(fileInterval, rankInterval));
    }
}
