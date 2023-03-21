package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.domain.Team;
import chess.domain.piece.ordinary.King;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

    King king = new King(Team.BLACK);

    @ParameterizedTest
    @CsvSource({"1,2"})
    void canMove_fail(int fileInterval, int rankInterval) {
        assertThrows(IllegalArgumentException.class,
                () -> king.validateMovement(fileInterval, rankInterval));
    }

    @ParameterizedTest
    @CsvSource({"1,1"})
    void canMove_success(int fileInterval, int rankInterval) {
        assertDoesNotThrow(() -> king.validateMovement(fileInterval, rankInterval));
    }
}
