package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.domain.Team;
import chess.domain.piece.ordinary.Queen;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenTest {
    Queen queen = new Queen(Team.BLACK);

    @ParameterizedTest
    @CsvSource({"1,2"})
    void canMove_fail(int fileInterval, int rankInterval) {
        assertThrows(IllegalArgumentException.class,
                () -> queen.validateMovement(fileInterval, rankInterval));    }

    @ParameterizedTest
    @CsvSource({"1,1", "3,0"})
    void canMove_success(int fileInterval, int rankInterval) {
        assertDoesNotThrow(() -> queen.validateMovement(fileInterval, rankInterval));
    }
}
