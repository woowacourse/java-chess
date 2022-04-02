package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER;
import static org.junit.jupiter.params.ParameterizedTest.DISPLAY_NAME_PLACEHOLDER;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ColorTest {

    @DisplayName("반대의 컬러를 반환한다.")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " [" + ARGUMENTS_PLACEHOLDER + "]")
    @CsvSource(value = {"BLACK, WHITE", "WHITE, BLACK", "EMPTY, EMPTY"})
    void getReverseColor(Color origin, Color expected) {
        assertThat(origin.getReverseColor()).isEqualTo(expected);
    }

    @DisplayName("Color가 Black인지 확인한다.")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " [" + ARGUMENTS_PLACEHOLDER + "]")
    @CsvSource(value = {"BLACK, true", "WHITE, false", "EMPTY, false"})
    void isBlack(Color color, boolean expected) {
        assertThat(color.isBlack()).isEqualTo(expected);
    }

    @DisplayName("Color가 White인지 확인한다.")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " [" + ARGUMENTS_PLACEHOLDER + "]")
    @CsvSource(value = {"BLACK, false", "WHITE, true", "EMPTY, false"})
    void isWhite(Color color, boolean expected) {
        assertThat(color.isWhite()).isEqualTo(expected);
    }

    @DisplayName("Color가 Empty인지 확인한다.")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " [" + ARGUMENTS_PLACEHOLDER + "]")
    @CsvSource(value = {"BLACK, false", "WHITE, false", "EMPTY, true"})
    void isEmpty(Color color, boolean expected) {
        assertThat(color.isEmpty()).isEqualTo(expected);
    }
}