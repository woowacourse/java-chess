package chess.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ColorTest {

    @ParameterizedTest
    @DisplayName("진영의 색깔이 검은색인지 확인")
    @CsvSource(value = {"WHITE:false", "NOTHING:false", "BLACK:true"}, delimiter = ':')
    void isBlack(Color color, boolean isBlack) {
        assertThat(color.isBlack()).isEqualTo(isBlack);
    }
}
