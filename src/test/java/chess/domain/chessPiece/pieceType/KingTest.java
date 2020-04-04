package chess.domain.chessPiece.pieceType;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {
    @Test
    void King_PieceColor_GenerateInstance() {
        assertThat(new King(PieceColor.BLACK)).isInstanceOf(King.class);
    }

    @Test
    void getName_ReturnName() {
        assertThat(new King(PieceColor.BLACK).getName()).isEqualTo("K");
        assertThat(new King(PieceColor.WHITE).getName()).isEqualTo("k");
    }
}