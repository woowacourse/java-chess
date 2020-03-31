package chess.domain.chessPiece.pieceType;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {
    @Test
    void Bishop_PieceColor_GenerateInstance() {
        assertThat(new Bishop(PieceColor.BLACK)).isInstanceOf(Bishop.class);
    }

    @Test
    void getName_ReturnName() {
        assertThat(new Bishop(PieceColor.BLACK).getName()).isEqualTo("B");
        assertThat(new Bishop(PieceColor.WHITE).getName()).isEqualTo("b");
    }
}