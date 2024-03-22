package chess.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;

class PositionParserTest {
    @ParameterizedTest
    @ValueSource(strings = {"", "a", "1", "A1", "aa1", "a10"})
    @DisplayName("유효하지 않은 좌표값을 입력할 경우 예외가 발생한다.")
    void validatePattern() {
        assertThatCode(() -> PositionParser.)
    }
}
