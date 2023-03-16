package chess.domain.state;

import chess.domain.ColorCompareResult;
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

    @Test
    void 폰은_초기상태에_앞으로_두칸_갈_수_있다() {
        //given
        InitialPawnState initialPawnState = new InitialPawnState();

        //when, then
        assertTrue(() -> initialPawnState.canMove(0, 2, ColorCompareResult.EMPTY));
    }

    @Test
    void 폰은_딱_한번_앞으로_두_칸_전진할_수_있다() {
        //given
        MoveState pawnState = new InitialPawnState();

        //when
        boolean movable = pawnState.canMove(0, 2, ColorCompareResult.EMPTY);
        if (movable) {
            pawnState = pawnState.getNextState();
        }

        // then
        MoveState finalState = pawnState;
        assertFalse(() -> finalState.canMove(0, 2, ColorCompareResult.EMPTY));
    }

    @Test
    void 폰_대각선_움직임_테스트() {
        //given
        MoveState pawnState = new InitialPawnState();

        //when, then
        assertTrue(() -> pawnState.canMove(1, 1, ColorCompareResult.DIFFERENT_COLOR));
    }

    @ParameterizedTest
    @ValueSource(strings = {"EMPTY", "SAME_COLOR"})
    void 폰_대각선_움직임_색_예외_테스트(ColorCompareResult colorCompareResult) {
        //given
        MoveState pawnState = new InitialPawnState();

        //when, then
        assertFalse(() -> pawnState.canMove(1, 1, colorCompareResult));
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 0", "1, 2", "2, 1"})
    void 폰_대각선_움직임_좌표_예외_테스트(int xChange, int yChange) {
        //given
        MoveState pawnState = new InitialPawnState();

        //when, then
        assertFalse(() -> pawnState.canMove(xChange, yChange, ColorCompareResult.DIFFERENT_COLOR));
    }
}
