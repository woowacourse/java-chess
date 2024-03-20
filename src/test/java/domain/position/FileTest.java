package domain.position;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileTest {

    @DisplayName("File 객체의 value를 뺀다.")
    @Test
    void subtractFile() {
        File source = new File('a');
        File target = new File('b');

        Assertions.assertThat(source.subtract(target)).isEqualTo(-1);
    }
}
