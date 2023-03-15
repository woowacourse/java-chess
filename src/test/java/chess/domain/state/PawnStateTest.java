package chess.domain.state;

import chess.domain.ColorCompareResult;
import chess.domain.exception.IllegalPieceMoveException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PawnStateTest {

    @Test
    void 폰은_초기상태에_앞으로_두칸_갈_수_있다() {
        //given
        InitialPawnState initialPawnState = new InitialPawnState();

        //when, then
        assertDoesNotThrow(() -> initialPawnState.move(0, 2, ColorCompareResult.EMPTY));
    }

    @Test
    void 폰은_딱_한번_앞으로_두_칸_전진할_수_있다() {
        //given
        MoveState pawnState = new InitialPawnState();

        //when
        pawnState = pawnState.move(0, 2, ColorCompareResult.EMPTY);

        // then
        MoveState finalPawnState = pawnState;
        assertThatThrownBy(() -> finalPawnState.move(0, 2, ColorCompareResult.EMPTY))
                .isInstanceOf(IllegalPieceMoveException.class);
    }

    @Test
    void 폰_대각선_움직임_테스트() {
        //given
        MoveState pawnState = new InitialPawnState();

        //when, then
        assertDoesNotThrow(() -> pawnState.move(1, 1, ColorCompareResult.DIFFERENT_COLOR));
    }

    @ParameterizedTest
    @ValueSource(strings = {"EMPTY", "SAME_COLOR"})
    void 폰_대각선_움직임_색_예외_테스트(ColorCompareResult colorCompareResult) {
        //given
        MoveState pawnState = new InitialPawnState();

        //when, then
        assertThatThrownBy(() -> pawnState.move(1, 1, colorCompareResult))
                .isInstanceOf(IllegalPieceMoveException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 0", "1, 2", "2, 1"})
    void 폰_대각선_움직임_좌표_예외_테스트(int xChange, int yChange) {
        //given
        MoveState pawnState = new InitialPawnState();

        //when, then
        assertThatThrownBy(() -> pawnState.move(xChange, yChange, ColorCompareResult.DIFFERENT_COLOR))
                .isInstanceOf(IllegalPieceMoveException.class);
    }
}
