package chess.domain.chessPiece.pieceType;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {
    @Test
    void Rook_PieceColor_GenerateInstance() {
        assertThat(new Rook(PieceColor.BLACK)).isInstanceOf(Rook.class);
    }

    @Test
    void getName_ReturnName() {
        assertThat(new Rook(PieceColor.BLACK).getName()).isEqualTo("R");
        assertThat(new Rook(PieceColor.WHITE).getName()).isEqualTo("r");
    }
}