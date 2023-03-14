package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SquareTest {

    @Test
    @DisplayName("칸은 말을 가지고 있어야 한다")
    void getPiece() {
        Square square = new Square(new Piece(PieceType.QUEEN, Camp.WHITE));

        assertThat(square.getPiece()).isNotNull();
    }

    @Test
    @DisplayName("칸은 말을 가지고 있지 않을 수도 있다")
    void getPieceNull() {
        Square square = new Square();

        assertThat(square.getPiece()).isNull();
    }
}
