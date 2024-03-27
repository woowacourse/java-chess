package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FileTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new File('a'))
                .doesNotThrowAnyException();
    }

    @DisplayName("열은 소문자 a부터 h까지의 값만 허용한다.")
    @ParameterizedTest
    @ValueSource(chars = {'A', 'i'})
    void checkValue(char source) {
        assertThatThrownBy(() -> new File(source))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
