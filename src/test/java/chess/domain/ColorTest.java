package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ColorTest {

    @DisplayName("진영을 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"BLACK,true", "WHITE,false"})
    void 진영을_확인한다(Color color, boolean expected) {
        assertThat(color.isBlack()).isEqualTo(expected);
    }
}
