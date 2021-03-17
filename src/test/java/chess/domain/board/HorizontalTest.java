package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HorizontalTest {
    @Test
    @DisplayName("입력받은 문자열로 Horizontal 탐색")
    void find() {
        Horizontal expectedHorizontal = Horizontal.find("a");
        assertEquals(expectedHorizontal, Horizontal.A);
    }

    @Test
    @DisplayName("가중치로 Horizontal 탐색")
    void findFromWeight() {
        Horizontal expectedHorizontal = Horizontal.findFromWeight(4);
        assertEquals(expectedHorizontal, Horizontal.D);
    }
}