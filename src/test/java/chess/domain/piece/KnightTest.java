package chess.domain.piece;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.domain.Team;
import chess.domain.piece.ordinary.Knight;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {

    Knight knight = new Knight(Team.BLACK);

    @ParameterizedTest
    @CsvSource({"1,1"})
    void canMove_fail(int fileInterval, int rankInterval) {
        assertThrows(IllegalArgumentException.class,
                () -> knight.validateMovement(fileInterval, rankInterval));    }

    @ParameterizedTest
    @CsvSource({"2,1"})
    void canMove_success(int fileInterval, int rankInterval) {
        assertDoesNotThrow(() -> knight.validateMovement(fileInterval, rankInterval));
    }
}
