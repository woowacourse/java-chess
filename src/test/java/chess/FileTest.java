package chess;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileTest {
    @DisplayName("잘못된 x 좌표값이 들어왔을 때 예외 출력")
    @Test
    void ofTest() {
        Assertions.assertThatThrownBy(() -> {
            File.of("i");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}