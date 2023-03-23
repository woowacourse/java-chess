package domain.point;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileTest {
    @Test
    @DisplayName("주어진 심볼에 알맞는 File 객체를 찾을 수 있다.")
    void findBySymbol() {
        assertThat(File.findBySymbol("b")).isEqualTo(File.B);
    }

    @Test
    @DisplayName("유효하지 않은 심볼 값으로 파일을 찾으려 시도하면 예외가 발생한다.")
    void findByInvalidSymbol() {
        assertThatThrownBy(() -> File.findBySymbol("k"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}