package model.piece;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ColorTest {

    @Test
    @DisplayName("각 색깔은 자신의 다음 차례의 색깔을 반환한다. (BLACK -> WHITE)")
    void next_ShouldReturnWhite_WhenBlack() {
        Color color = Color.BLACK;
        Color nextColor = color.opponent();
        assertEquals(Color.WHITE, nextColor);
    }

    @Test
    @DisplayName("각 색깔은 자신의 다음 차례의 색깔을 반환한다.(WHITE -> BLACK)")
    void next_ShouldReturnBlack_WhenWhite() {
        Color color = Color.WHITE;
        Color nextColor = color.opponent();
        assertEquals(Color.BLACK, nextColor);
    }

    @Test
    @DisplayName("색깔이 없을 때에는 WHITE을 반환한다.")
    void next_ShouldReturnBlack_WhenUnColored() {
        Color color = Color.UN_COLORED;
        Color nextColor = color.opponent();
        assertEquals(Color.WHITE, nextColor);
    }
}
