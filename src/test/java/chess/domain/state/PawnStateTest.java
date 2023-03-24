package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.piece.ColorCompareResult;
import chess.domain.piece.state.InitialPawnState;
import chess.domain.piece.state.MoveState;
import chess.domain.piece.state.MovedPawnState;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings({"NonAsciiCharacters"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PawnStateTest {

    private static final InitialPawnState initialPawnState = InitialPawnState.getInstance();
    private static final MovedPawnState movedPawnState = MovedPawnState.getInstance();

    @Test
    void 폰은_초기상태에_앞으로_두칸_갈_수_있다() {
        //expect
        assertTrue(() -> initialPawnState.canMove(0, 2, ColorCompareResult.EMPTY));
    }

    @Test
    void 폰은_딱_한번_앞으로_두_칸_전진할_수_있다() {
        // given
        // when
        boolean firstMove = initialPawnState.canMove(0, 2, ColorCompareResult.EMPTY);
        MoveState finalState = initialPawnState.getNextState();
        boolean secondResult = finalState.canMove(0, 2, ColorCompareResult.EMPTY);

        // then
        assertTrue(firstMove);
        assertFalse(secondResult);
    }

    @Test
    void 폰_대각선_움직임_테스트() {
        //expect
        assertTrue(() -> initialPawnState.canMove(1, 1, ColorCompareResult.DIFFERENT_COLOR));
    }

    @ParameterizedTest
    @ValueSource(strings = {"EMPTY", "SAME_COLOR"})
    void 폰_대각선_움직임_색_예외_테스트(ColorCompareResult colorCompareResult) {
        //expect
        assertFalse(() -> initialPawnState.canMove(1, 1, colorCompareResult));
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 0", "1, 2", "2, 1"})
    void 폰_대각선_움직임_좌표_예외_테스트(int xChange, int yChange) {
        //expect
        assertFalse(() -> initialPawnState.canMove(xChange, yChange, ColorCompareResult.DIFFERENT_COLOR));
    }

    @Test
    void 초기_폰의_다음_상태는_움직인_폰_상태이다() {
        //expect
        assertSame(initialPawnState.getNextState(), movedPawnState.getNextState());
    }

    @Test
    void 움직인_폰의_다음_상태는_움직인_폰_상태이다() {
        //expect
        assertSame(movedPawnState, movedPawnState.getNextState());
    }

    @Test
    void 폰의_점수는_1점이다() {
        //expect
        assertThat(initialPawnState.getScore()).isEqualTo(1, withPrecision(0.0001));
    }

    @Test
    void 폰은_킹이_아니다() {
        //expect
        assertFalse(initialPawnState.isKing());
    }

    @Test
    void 움직인_폰의_타입을_가져와도_폰이다() {
        //expect
        assertSame(initialPawnState.getType(), movedPawnState.getType());
    }
}
