package chess.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

final class ColorTest {

    @ParameterizedTest(name = "{0} 색상은 {1} 색상으로 반환한다.")
    @CsvSource(value = {
            "WHITE,BLACK",
            "BLACK,WHITE"
    })
    void changeColor(final Color actual, final Color expected) {
        assertThat(Color.changeColor(actual)).isEqualTo(expected);
    }
}
