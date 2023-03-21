package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.*;

import chess.domain.Team;
import chess.domain.piece.pawn.InitialPawn;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class InitialPawnTest {

    @ParameterizedTest
    @CsvSource({"2,0,BLACK", "-1,0,BLACK", "0,3,BLACK", "0,1,BLACK", "0,-1,WHITE"})
    void canMove_fail(int fileInterval, int rankInterval, Team team) {
        InitialPawn initialPawn = new InitialPawn(team);
        assertThrows(IllegalArgumentException.class,
                () -> initialPawn.validateMovement(fileInterval, rankInterval));
    }

    @ParameterizedTest
    @CsvSource({"0,1,WHITE"})
    void canMove_success(int fileInterval, int rankInterval, Team team) {
        InitialPawn initialPawn = new InitialPawn(team);
        assertDoesNotThrow((() -> initialPawn.validateMovement(fileInterval, rankInterval)));
    }
}
