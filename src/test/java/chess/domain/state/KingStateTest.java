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
public class KingStateTest {
    @ParameterizedTest
    @CsvSource(value = {"1, 1", "1, 0", "0, -1", "-1, 1", "0, 1"})
    void 킹은_대각선_혹은_직선으로_1칸_움직일_수_있다(int xChange, int yChange) {
        //given
        KingState kingState = new KingState();

        //expect
        assertTrue(() -> kingState.canMove(xChange, yChange, DIFFERENT_COLOR));
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 3", "0, 2", "-2, -2", "2, 0", "-1, -2"})
    void 킹은_대각선이나_직선으로_1칸만_이동_가능하다(int xChange, int yChange) {
        //given
        KingState kingState = new KingState();

        //expect
        assertFalse(() -> kingState.canMove(xChange, yChange, DIFFERENT_COLOR));
    }
}
