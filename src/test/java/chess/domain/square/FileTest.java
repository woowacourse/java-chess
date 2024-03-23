package chess.domain.square;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("파일")
class FileTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 8})
    @DisplayName("범위 밖의 값일 경우 예외가 발생한다.")
    void validateRange(int value) {
        assertThatCode(() -> File.from(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("범위 밖의 파일 입니다.");
    }
}
