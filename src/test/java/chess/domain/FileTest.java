package chess.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileTest {

    @Test
    @DisplayName("좌표가 유효하지 않을 경우 예외가 발생한다.")
    void occurExceptionIfFileIsInvalid() {
        assertThatCode(() -> File.from("i"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(File.ERROR_NOT_EXIST_FILE);
    }
}
