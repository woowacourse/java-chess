package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ColorTest {

    @ParameterizedTest
    @CsvSource({"BLACK, true", "WHITE, false"})
    @DisplayName("검정 색인지 확인한다")
    void isBlackTest(final Color color, final boolean expected) {
        final boolean actual = color.isBlack();

        assertThat(actual).isEqualTo(expected);
    }
}
