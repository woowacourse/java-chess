package chess.domain.state;

import chess.domain.ColorCompareResult;
import chess.domain.exception.IllegalPieceMoveException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class KnightStateTest {

    @ParameterizedTest
    @CsvSource(value = {"2, 1", "2, -1", "1, 2", "-1, 2"})
    void 나이트는_한_방향으로_이동한_후_해당_방향으로_대각선_이동_테스트(int xChange, int yChange) {
        //given
        KnightState knightState = new KnightState();

        //expect
        assertDoesNotThrow(() -> knightState.move(xChange, yChange, ColorCompareResult.DIFFERENT_COLOR));
    }


    @ParameterizedTest
    @CsvSource(value = {"2, 2", "2, -2", "0, 2"})
    void 나이트_이동_예외_테스트(int xChange, int yChange) {
        //given
        KnightState knightState = new KnightState();

        //expect
        assertThatThrownBy(() -> knightState.move(xChange, yChange, ColorCompareResult.DIFFERENT_COLOR))
                .isInstanceOf(IllegalPieceMoveException.class);
    }
}
