package chess.domain.attribute;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FileTest {
    @DisplayName("1~8 범위를 벗어나는 열 번호를 입력하면 예외가 발생한다.")
    @ValueSource(ints = {0, 9})
    @ParameterizedTest
    void constructor(int column) {
        assertThatThrownBy(() -> File.of(column))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("파일은 1~8 사이로 입력해주세요: " + column);
    }
}
