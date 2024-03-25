package chess.domain.square;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("파일")
class FileTest {

    @Test
    @DisplayName("알파벳 문자를 정상적으로 변환한다.")
    void fromTest() {
        // given
        File file = File.from('c');

        // when & then
        assertThat(file).isEqualTo(File.C);
    }

    @ParameterizedTest
    @ValueSource(chars = {'z', 'A'})
    @DisplayName("범위 밖의 값일 경우 예외가 발생한다.")
    void validateRangeTest(char input) {
        assertThatCode(() -> File.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("범위 밖의 파일 입니다.");
    }
}
