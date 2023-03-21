package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ColorTest {

    @ParameterizedTest
    @CsvSource({"BLACK, WHITE", "WHITE, BLACK", "BLANK, BLANK"})
    @DisplayName("반대 색 혹은 빈 색을 반환한다")
    void getOppositeColor_test(final Color color, final Color expectedColor) {
        final Color actualColor = color.getOppositeColor();

        assertThat(actualColor).isEqualTo(expectedColor);
    }
}
