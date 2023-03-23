package chess.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class ColorTest {

    @ParameterizedTest
    @CsvSource({"BLACK, WHITE", "WHITE, BLACK", "BLANK, BLANK"})
    void 반대_색_혹은_빈_색을_반환한다(final Color color, final Color expectedColor) {
        final Color actualColor = color.getOppositeColor();

        assertThat(actualColor).isEqualTo(expectedColor);
    }
}
