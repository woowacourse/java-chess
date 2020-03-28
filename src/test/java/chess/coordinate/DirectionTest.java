package chess.coordinate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DirectionTest {

    @DisplayName("-1~+1 을 벗어나는 경우와 0,0을 입력한 경우 Exception 발생")
    @ParameterizedTest
    @CsvSource(value = {"2,0", "0,0"})
    void findByValue(int fileVariation, int rankVariation) {

        assertThatThrownBy(() -> Direction.findByValue(fileVariation, rankVariation))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("fileVariation : %d, rankVariation : %d, 입력값을 확인하시오.", fileVariation, rankVariation);
    }
}