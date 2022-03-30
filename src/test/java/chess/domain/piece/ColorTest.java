package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ColorTest {

    @ParameterizedTest
    @CsvSource(value = {"BLACK, WHITE", "WHITE, BLACK", "EMPTY, EMPTY"})
    void getReverseColor(Color origin, Color expected) {
        Color reverseColor = origin.getReverseColor();

        assertThat(reverseColor).isEqualTo(expected);
    }
}
