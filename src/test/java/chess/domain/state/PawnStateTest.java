package chess.domain.state;

import chess.domain.ColorCompareResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PawnStateTest {

    private PawnState pawnState;

    @BeforeEach
    void setting() {
        pawnState = new PawnState();
    }


    @Test
    void 폰은_초기상태에_앞으로_두칸_갈_수_있다() {
        //expect
        assertTrue(() -> pawnState.canMove(0, 2, ColorCompareResult.EMPTY));
    }

    @Test
    void 폰은_딱_한번_앞으로_두_칸_전진할_수_있다() {
        // given
        // when
        boolean firstMove = pawnState.canMove(0, 2, ColorCompareResult.EMPTY);
        boolean secondResult = pawnState.canMove(0, 2, ColorCompareResult.EMPTY);

        // then
        assertTrue(firstMove);
        assertFalse(secondResult);
    }

    @Test
    void 폰_대각선_움직임_테스트() {
        //expect
        assertTrue(() -> pawnState.canMove(1, 1, ColorCompareResult.DIFFERENT_COLOR));
    }

    @ParameterizedTest
    @ValueSource(strings = {"EMPTY", "SAME_COLOR"})
    void 폰_대각선_움직임_색_예외_테스트(ColorCompareResult colorCompareResult) {
        //expect
        assertFalse(() -> pawnState.canMove(1, 1, colorCompareResult));
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 0", "1, 2", "2, 1"})
    void 폰_대각선_움직임_좌표_예외_테스트(int xChange, int yChange) {
        //expect
        assertFalse(() -> pawnState.canMove(xChange, yChange, ColorCompareResult.DIFFERENT_COLOR));
    }
}
