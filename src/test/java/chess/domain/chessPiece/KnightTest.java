package chess.domain.chessPiece;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    @Test
    void Knight_MovableStrategy_GenerateInstance() {
        assertThat(new Knight(PieceColor.BLACK)).isInstanceOf(Knight.class);
    }

    @Test
    void getName_ReturnName() {
        String expected = "N";

        assertThat(new Knight(PieceColor.BLACK).getName()).isEqualTo(expected);
    }
}