package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileTest {
    @DisplayName("File 범위 검증 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"i", "z", "0"})
    void whenFileOutOfBound(String testString) {
        assertThatThrownBy(() -> File.from(testString))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("해당하는 문자의 File이 없습니다.");
    }
}