package chess.domain.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.domain.ColorCompareResult.DIFFERENT_COLOR;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class QueenStateTest {

    private QueenState queenState;

    @BeforeEach
    void setting() {
        queenState = new QueenState();
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 1", "3, 0", "-5, -5", " 0, -7", "5, -5"})
    void 퀸은_대각선_혹은_직선으로_움직일_수_있다(int xChange, int yChange) {
        //expect
        assertTrue(() -> queenState.canMove(xChange, yChange, DIFFERENT_COLOR));
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 3", "3, 2", "-5, -6", " 3, -7", "2, -5"})
    void 퀸이_대각선이_아닌_방향으로_움직일_수_없다(int xChange, int yChange) {
        //expect
        assertFalse(() -> queenState.canMove(xChange, yChange, DIFFERENT_COLOR));
    }
}
