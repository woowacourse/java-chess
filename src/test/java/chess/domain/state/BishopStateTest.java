package chess.domain.state;

import static chess.domain.piece.ColorCompareResult.DIFFERENT_COLOR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.piece.state.BishopState;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings({"NonAsciiCharacters"})
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

    @Test
    void 비숍의_다음_상태는_처음과_같다() {
        //expect
        assertSame(bishopState, bishopState.getNextState());
    }

    @Test
    void 비숍의_점수는_3점이다() {
        //expect
        assertThat(bishopState.getScore()).isEqualTo(3, withPrecision(0.0001));
    }

    @Test
    void 비숍은_킹이_아니다() {
        //expect
        assertFalse(bishopState.isKing());
    }
}
