package chess.domain.state;

import chess.domain.exception.IllegalPieceMoveException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.domain.ColorCompareResult.DIFFERENT_COLOR;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RookStateTest {

    @ParameterizedTest
    @CsvSource(value = {"1, 0", "3, 0", "-5, 0", "0, -7", "0, -5"})
    void 룩은_직선으로_움직일_수_있다(int xChange, int yChange) {
        //given
        RookState RookState = new RookState();

        //expect
        assertDoesNotThrow(() -> RookState.move(xChange, yChange, DIFFERENT_COLOR));
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 3", "3, 2", "-5, -6", " 3, -7", "2, -5"})
    void 비숍이_직선이_아닌_방향으로_움직일_수_없다(int xChange, int yChange) {
        //given
        RookState RookState = new RookState();

        //expect
        assertThatThrownBy(() -> RookState.move(xChange, yChange, DIFFERENT_COLOR))
                .isInstanceOf(IllegalPieceMoveException.class);
    }
}
