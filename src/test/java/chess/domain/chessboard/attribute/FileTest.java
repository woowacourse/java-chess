package chess.domain.chessboard.attribute;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FileTest {

    @DisplayName("a~h 범위를 벗어나는 문자를 입력하면 예외가 발생한다.")
    @ValueSource(chars = {'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p'})
    @ParameterizedTest
    void constructor(char invalidValue) {
        assertThatThrownBy(() -> File.from(invalidValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("파일은 a~h 사이로 입력해주세요: " + invalidValue);
    }

    @DisplayName("0~7 범위를 벗어나는 열 번호를 입력하면 예외가 발생한다.")
    @ValueSource(ints = {-1, 8})
    @ParameterizedTest
    void constructor(int invalidColumn) {
        assertThatThrownBy(() -> File.from(invalidColumn))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("파일은 a~h 사이로 입력해주세요: " + invalidColumn);
    }
}
