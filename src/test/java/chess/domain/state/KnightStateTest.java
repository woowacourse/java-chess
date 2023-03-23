package chess.domain.state;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.piece.ColorCompareResult;
import chess.domain.piece.state.KnightState;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings({"NonAsciiCharacters"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class KnightStateTest {

    private static final KnightState knightState = KnightState.getInstance();

    @ParameterizedTest
    @CsvSource(value = {"2, 1", "2, -1", "1, 2", "-1, 2"})
    void 나이트는_한_방향으로_이동한_후_해당_방향으로_대각선_이동_테스트(int xChange, int yChange) {
        //expect
        assertTrue(() -> knightState.canMove(xChange, yChange, ColorCompareResult.DIFFERENT_COLOR));
    }


    @ParameterizedTest
    @CsvSource(value = {"2, 2", "2, -2", "0, 2"})
    void 나이트_이동_예외_테스트(int xChange, int yChange) {
        //expect
        assertFalse(() -> knightState.canMove(xChange, yChange, ColorCompareResult.DIFFERENT_COLOR));
    }

    @Test
    void 나이트의_다음_상태는_처음과_같다() {
        //expect
        assertSame(knightState, knightState.getNextState());
    }

    @Test
    void 나이트의_점수는_0점이다() {
        //expect
        assertSame(0, knightState.getScore(List.of()));
    }
}
