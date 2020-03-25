package chess.domain.chessPiece;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {
    @Test
    void King_MovableStrategy_GenerateInstance() {
        assertThat(new King(PieceColor.BLACK)).isInstanceOf(King.class);
    }

    @Test
    void getName_ReturnName() {
        String expected = "K";

        assertThat(new King(PieceColor.BLACK).getName()).isEqualTo(expected);
    }
}