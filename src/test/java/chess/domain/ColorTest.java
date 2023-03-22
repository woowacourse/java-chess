package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings({"NonAsciiCharacters"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ColorTest {

    @ParameterizedTest
    @CsvSource(value = {"BLACK, -1", "WHITE, 1", "NONE, 0"})
    void 색에_따라_값이_변화하는_테스트(Color color, int result) {
        //expect
        assertThat(color.colorForwardDirection(1))
                .isEqualTo(result);
    }
}
