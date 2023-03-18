package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class ColorTest {

    @ParameterizedTest(name = "입력한 Color와 다른 값인지 확인한다. 대상: BLACK 입력: {0}, 결과: {1}")
    @CsvSource({"WHITE, true", "BLACK, false", "EMPTY, false"})
    void 입력한_Color가_아닌지_확인한다(final Color color, final boolean result) {
        // expect
        assertThat(BLACK.isOpponent(color)).isEqualTo(result);
    }

    @ParameterizedTest(name = "다음 턴을 반한한다 현재: {0}, 결과: {1}")
    @CsvSource({"WHITE, BLACK", "BLACK, WHITE"})
    void 다음_턴을_반환한다(final Color current, final Color next) {
        // expect
        assertThat(current.nextTurn()).isEqualTo(next);
    }
}
