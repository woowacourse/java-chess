package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileTest {

    @Test
    @DisplayName("File 간의 차이 계산")
    void subtract() {
        File file1 = new File(1);
        File file2 = new File(2);

        assertThat(file1.subtract(file2)).isEqualTo(-1);
    }
}
