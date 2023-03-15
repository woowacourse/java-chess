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
class BishopStateTest {
    @ParameterizedTest
    @CsvSource(value = {"1, 1", "3, 3", "-5, -5", " 7, -7", "5, -5"})
    void 비숍이_대각선으로_움직일_수_있다(int xChange, int yChange) {
        //given
        BishopState bishopState = new BishopState();

        //expect
        assertDoesNotThrow(() -> bishopState.move(xChange, yChange, DIFFERENT_COLOR));
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 3", "3, 2", "-5, -6", " 3, -7", "2, -5"})
    void 비숍이_대각선이_아닌_방향으로_움직일_수_없다(int xChange, int yChange) {
        //given
        BishopState bishopState = new BishopState();

        //expect
        assertThatThrownBy(() -> bishopState.move(xChange, yChange, DIFFERENT_COLOR))
                .isInstanceOf(IllegalPieceMoveException.class);
    }
}
