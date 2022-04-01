package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileTest {

    @Test
    @DisplayName("숫자에 해당하는 File을 찾아, Rank와 병합하기 위해 문자열로 값을 반환한다.")
    void of() {
        // given
        int value = 1;
        // when
        String actual = File.of(value).getValue();
        String expected = "1";
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("입력된 value가 유효하지 않은 범위이면 예외를 발생시킨다.")
    void of_exception() {
        // given
        int value = 9;
        // then
        assertThatThrownBy(() -> File.of(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 범위입니다.");
    }
}
