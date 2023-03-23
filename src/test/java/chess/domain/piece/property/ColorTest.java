package chess.domain.piece.property;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ColorTest {

    @ParameterizedTest
    @CsvSource({"BLACK, WHITE", "WHITE, BLACK", "BLANK, BLANK"})
    @DisplayName("반대되는 색상을 얻는다")
    void opposite_color_test(final Color originalColor, final Color expectedOpposite) {
        final Color actualOpposite = originalColor.getOppositeColor();

        assertThat(actualOpposite).isEqualTo(expectedOpposite);
    }
}
