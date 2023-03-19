package controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class CoordinateAdapterTest {

    @Test
    @DisplayName("좌표가 두 글자가 아닌지를 검증한다")
    void validateLength() {
        String target = "a123";

        assertThatThrownBy(() -> CoordinateAdapter.convert(target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("X축 좌표가 포맷에 맞는지를 검증한다")
    void validateXFormat() {
        String target = "aa";

        assertThatThrownBy(() -> CoordinateAdapter.convert(target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Y축 좌표가 포맷에 맞는지를 검증한다")
    void validateYFormat() {
        String target = "ㅁ1";

        assertThatThrownBy(() -> CoordinateAdapter.convert(target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Y축 좌표가 알파벳 소문자가 맞는지를 검증한다")
    void validateYFormatAlphabetLowerCase() {
        String target = "a1";

        assertThatCode(() -> CoordinateAdapter.convert(target))
                .doesNotThrowAnyException();
    }
}
