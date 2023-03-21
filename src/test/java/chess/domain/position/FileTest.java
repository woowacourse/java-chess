package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class FileTest {

    @ParameterizedTest(name = "입력: {0}")
    @ValueSource(chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'})
    @DisplayName("주어진 입력값에 따라 File을 생성할 수 있는지 확인")
    void ofFileTest(final char value) {
        // given
        final char expected = value;

        // when
        File file = File.of(value);
        final char actual = file.value();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
