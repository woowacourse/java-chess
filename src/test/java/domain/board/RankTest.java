package domain.board;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {
    @DisplayName("문자열을 받아 Rank를 생성할 수 있다.")
    @Test
    void createTest() {
        //given
        final String rank = "1";

        //when

        //then
        assertDoesNotThrow(() -> Rank.from(rank));
    }

    @DisplayName("1 ~ 8이 아니면 예외가 발생한다.")
    @Test
    void createFail() {
        //given
        final String rank = "0";

        //when

        //then
        assertThrows(IllegalArgumentException.class,
                () -> File.from(rank));
    }
}
