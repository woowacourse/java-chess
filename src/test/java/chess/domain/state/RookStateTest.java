package chess.domain.state;

import static chess.domain.piece.ColorCompareResult.DIFFERENT_COLOR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.piece.state.RookState;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings({"NonAsciiCharacters"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RookStateTest {

    private static final RookState rookState = RookState.getInstance();

    @ParameterizedTest
    @CsvSource(value = {"1, 0", "3, 0", "-5, 0", "0, -7", "0, -5"})
    void 룩은_직선으로_움직일_수_있다(int xChange, int yChange) {
        //expect
        assertTrue(() -> rookState.canMove(xChange, yChange, DIFFERENT_COLOR));
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 3", "3, 2", "-5, -6", " 3, -7", "2, -5"})
    void 비숍이_직선이_아닌_방향으로_움직일_수_없다(int xChange, int yChange) {
        //expect
        assertFalse(() -> rookState.canMove(xChange, yChange, DIFFERENT_COLOR));
    }

    @Test
    void 룩의_다음_상태는_처음과_같다() {
        //expect
        assertSame(rookState, rookState.getNextState());
    }

    @Test
    void 룩의_점수는_5점이다() {
        //expect
        assertThat(rookState.getScore()).isEqualTo(5, withPrecision(0.0001));
    }
}
