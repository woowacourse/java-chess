package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.MessageFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class PositionTest {

    @ParameterizedTest(name = "row {0}, column {1}")
    @CsvSource(value = {"-1,0", "0, -1", "8, 7", "7, 8"})
    @DisplayName("위치가 (0,0)에서 (7,7) 사이가 아닐 경우 예외 발생한다.")
    void validatePosition(int row, int column) {
        assertThatThrownBy(() -> new Position(row, column))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MessageFormat.format("체스판 범위에서 벗어났습니다. row: {0}, column: {1}", row, column));
    }
}
