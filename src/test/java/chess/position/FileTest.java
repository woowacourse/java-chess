package chess.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileTest {
    @DisplayName("File 객체값이 a-h 사이인지 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"n", "i", "z"})
    void invalidOfTest(String name) {
        assertThat(File.of(name)).isEqualTo(File.NONE);
    }

    @DisplayName("File.of() 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"a", "h"})
    void ofTest(String name) {
        assertThat(File.of(name)).isInstanceOf(File.class);
    }

    @DisplayName("valuesExceptNone 메서드 테스트")
    @Test
    void valuesExceptNoneTest() {
        File[] files = File.valuesExceptNone();
        assertThat(files.length).isEqualTo(8);
    }
}