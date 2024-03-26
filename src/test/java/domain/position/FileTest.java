package domain.position;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileTest {

    @DisplayName("File 객체의 value를 뺀다.")
    @Test
    void subtractFile() {
        File source = new File('a');
        File target = new File('b');

        assertThat(source.subtract(target)).isEqualTo(-1);
    }

    @DisplayName("File의 letter의 범위를 벗어나면 에러를 발생한다.")
    @Test
    void letterRange() {
        char file = 'i';

        assertThatThrownBy(() -> new File(file))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
