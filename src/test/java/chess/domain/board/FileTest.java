package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileTest {
    @DisplayName("File 열거 객체의 value를 비교한다")
    @Test
    void compareFileValue() {
        File file = File.C;

        assertThat(file.getValue()).isEqualTo("c");
    }
}