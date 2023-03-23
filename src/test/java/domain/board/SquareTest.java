package domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SquareTest {
    @Test
    @DisplayName("square의 좌표를 반환한다.")
    void toCoordinate() {
        Square square = new Square(File.H, Rank.FIVE);

        List<Integer> coordinate = square.toCoordinate();

        assertThat(coordinate).containsExactly(7, 4);
    }
}
