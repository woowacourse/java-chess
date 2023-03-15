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
public class KingStateTest {
    @ParameterizedTest
    @CsvSource(value = {"1, 1", "1, 0", "0, -1", "-1, 1", "0, 1"})
    void 킹은_대각선_혹은_직선으로_1칸_움직일_수_있다(int xChange, int yChange) {
        //given
        KingState kingState = new KingState();

        //expect
        assertDoesNotThrow(() -> kingState.move(xChange, yChange, DIFFERENT_COLOR));
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 3", "0, 2", "-2, -2", "2, 0", "-1, -2"})
    void 킹은_대각선이나_직선으로_1칸만_이동_가능하다(int xChange, int yChange) {
        //given
        KingState kingState = new KingState();

        //expect
        assertThatThrownBy(() -> kingState.move(xChange, yChange, DIFFERENT_COLOR))
                .isInstanceOf(IllegalPieceMoveException.class);
    }
}
