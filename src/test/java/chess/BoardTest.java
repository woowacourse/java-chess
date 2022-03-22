package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class BoardTest {

    @Test
    @DisplayName("체스 판은 32개의 말로 생성된다")
    void createBoard() {
        assertThatCode(Board::init).doesNotThrowAnyException();
    }
}
