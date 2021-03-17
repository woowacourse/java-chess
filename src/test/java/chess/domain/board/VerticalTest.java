package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VerticalTest {
    @Test
    @DisplayName("입력받은 문자열로 Vertical 탐색")
    void find() {
        Vertical expectedVertical = Vertical.find("2");
        assertEquals(expectedVertical, Vertical.TWO);
    }

    @Test
    @DisplayName("가중치로 Vertical 탐색")
    void findFromWeight() {
        Vertical expectedVertical = Vertical.findFromWeight(6);
        assertEquals(expectedVertical, Vertical.SIX);
    }
}