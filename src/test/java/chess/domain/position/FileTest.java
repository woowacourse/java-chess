package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileTest {

    @Test
    @DisplayName("문자열에 해당하는 File을 찾는다.")
    void of() {
        // given
        String value = "1";

        // when
        File file = File.of(value);
        String actual = file.getValue();

        // then
        assertThat(actual).isEqualTo(value);
    }

    @Test
    @DisplayName("입력된 value가 유효하지 않은 범위이면 예외를 발생시킨다.")
    void of_exception() {
        // given
        String value = "9";

        // when
        // then
        assertThatThrownBy(() -> File.of(value))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("유효하지 않은 범위입니다.");
    }
}