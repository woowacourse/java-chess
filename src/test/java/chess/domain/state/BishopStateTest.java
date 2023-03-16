package chess.domain.state;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.domain.ColorCompareResult.DIFFERENT_COLOR;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BishopStateTest {

    private static final BishopState bishopState = BishopState.getInstance();

    @ParameterizedTest
    @CsvSource(value = {"1, 1", "3, 3", "-5, -5", " 7, -7", "5, -5"})
    void 비숍이_대각선으로_움직일_수_있다(int xChange, int yChange) {
        //expect
        assertTrue(() -> bishopState.canMove(xChange, yChange, DIFFERENT_COLOR));
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 3", "3, 2", "-5, -6", " 3, -7", "2, -5"})
    void 비숍이_대각선이_아닌_방향으로_움직일_수_없다(int xChange, int yChange) {
        //expect
        assertFalse(() -> bishopState.canMove(xChange, yChange, DIFFERENT_COLOR));
    }
}
