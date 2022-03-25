package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ColorTest {

    @Test
    @DisplayName("색상 전환")
    void reverse() {
        assertThat(Color.WHITE.reverse()).isEqualTo(Color.BLACK);
    }

    @ParameterizedTest
    @CsvSource(value = {"WHITE,A,a", "BLACK,b,B"})
    @DisplayName("Color에 따른 문자케이스 변경")
    void convertToCase(Color color, String input, String expected) {
        String actual = color.convertToCase(input);
        assertThat(actual).isEqualTo(expected);
    }
}
