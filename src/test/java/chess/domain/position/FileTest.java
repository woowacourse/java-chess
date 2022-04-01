package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileTest {

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "7", "!", "."})
    @DisplayName("유효하지 않은 위치 입력시 예외 발생")
    void wrongPositionInput(String x) {
        assertThatThrownBy(() -> File.findColumn(x))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("올바르지 않은 file 위치 정보입니다.");
    }

    @ParameterizedTest
    @DisplayName("유효한 위치 입력시")
    @CsvSource(value = {"a:0", "h:7"}, delimiter = ':')
    void rightPositionInput(String input, int expected) {
        assertThat(File.findColumn(input)).isEqualTo(expected);
    }
}
