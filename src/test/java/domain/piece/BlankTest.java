package domain.piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.coordinate.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlankTest {

    @DisplayName("방향을 얻어낼 수 없다.")
    @Test
    void getDirectionException() {
        Blank blank = Blank.getInstance();

        assertThatThrownBy(() -> blank.getDirection(new Coordinate(2, 2), new Coordinate(3, 3)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이동할 말이 존재하지 않습니다.");
    }

    @DisplayName("특정 색상을 가지지 않는다.")
    @Test
    void hasSameColor() {
        Blank blank = Blank.getInstance();

        assertTrue(blank.hasSameColor(Color.BLACK));
        assertTrue(blank.hasSameColor(Color.WHITE));
    }
}
