package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SquareTest {

    @Test
    @DisplayName("칸은 말을 가지고 있어야 한다")
    void getPiece() {
        Square square = new Square(0, 0);
        assertThat(square.getPiece())
                .isInstanceOf(ConcretePiece.class);
    }

    @Test
    @DisplayName("칸은 말을 가지고 있지 않을 수도 있다")
    void getPieceNull() {
        Square square = new Square(2, 0);

        assertThat(square.getPiece())
                .isInstanceOf(EmptyPiece.class);
    }
}
