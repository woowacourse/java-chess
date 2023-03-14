package domain.board;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileTest {
    @DisplayName("문자열을 받아 File을 생성할 수 있다.")
    @Test
    void createTest() {
        //given
        final String file = "A";

        //when

        //then
        assertDoesNotThrow(() -> File.from(file));
    }

    @DisplayName("A ~ H가 아니면 예외가 발생한다.")
    @Test
    void createFail() {
        //given
        final String file = "Z";

        //when

        //then
        assertThrows(IllegalArgumentException.class,
                () -> File.from(file));
    }
}
