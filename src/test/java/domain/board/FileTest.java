package domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FileTest {

    @Test
    @DisplayName("현재 File과 특정 File의 차를 계산")
    void subtract() {
        File file1 = File.valueOf(1);
        File file2 = File.valueOf(2);

        assertThat(file1.subtract(file2)).isEqualTo(-1);
    }
}
