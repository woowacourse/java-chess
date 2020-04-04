package chess.domain.chessPiece.pieceType;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    @Test
    void Knight_PieceColor_GenerateInstance() {
        assertThat(new Knight(PieceColor.BLACK)).isInstanceOf(Knight.class);
    }

    @Test
    void getName_ReturnName() {
        assertThat(new Knight(PieceColor.BLACK).getName()).isEqualTo("N");
        assertThat(new Knight(PieceColor.WHITE).getName()).isEqualTo("n");
    }
}