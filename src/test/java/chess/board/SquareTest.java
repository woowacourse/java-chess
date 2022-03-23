package chess.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SquareTest {
    @Test
    @DisplayName("세로가 a이고 가로가 1이면 이름이 a1 이다")
    void squareName_a1() {
        Square square = new Square(File.A, Rank.ONE);

        assertThat(square.getName()).isEqualTo("a1");
    }

    @Test
    @DisplayName("세로가 h이고 가로가 3이면 이름이 h3 이다")
    void squareName_h3() {
        Square square = new Square(File.H, Rank.THREE);

        assertThat(square.getName()).isEqualTo("h3");
    }
}
