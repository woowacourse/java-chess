package chess.domain.board.position;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileTest {

    @Test
    @DisplayName("유효하지 않은 값으로 생성하려 할 때 예외를 발생시킨다.")
    void createException() {
        // given
        String invalidValue = "q";
        // when, then
        assertThatThrownBy(() -> File.from(invalidValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 file 값입니다.");
    }
}
