package chess.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LocationTest {

    @ParameterizedTest
    @CsvSource(value = {"0, 1", "1, 0", "9, 8", "8, 9"})
    @DisplayName("범위 밖을 벗어나면 예외가 발생한다.")
    void invalidLocationThrowsException(int row, int column) {
        assertThatThrownBy(() -> new Location(row, column))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("위치는 1부터 8 사이의 값이어야 합니다.");
    }
}
