package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class InitialPawnTest {

    @ParameterizedTest
    @CsvSource({"2,0,BLACK", "-1,0,BLACK", "0,3,BLACK", "0,1,BLACK", "0,-1,WHITE"})
    void canMove_fail(int fileInterval, int rankInterval, Color color) {
        InitialPawn initialPawn = new InitialPawn(color);
        assertThrows(IllegalArgumentException.class,
                () -> initialPawn.validateMovement(fileInterval, rankInterval));
    }

    @ParameterizedTest
    @CsvSource({"0,1,WHITE"})
    void canMove_success(int fileInterval, int rankInterval, Color color) {
        InitialPawn initialPawn = new InitialPawn(color);
        assertDoesNotThrow((() -> initialPawn.validateMovement(fileInterval, rankInterval)));
    }
}
