package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ColorTest {

    @DisplayName("반대되는 색상을 리턴한다.")
    @ParameterizedTest
    @CsvSource({"WHITE, BLACK", "BLACK, WHITE"})
    void colorOppositeTest(Color color, Color expected) {
        assertThat(color.opposite()).isEqualTo(expected);
    }

}