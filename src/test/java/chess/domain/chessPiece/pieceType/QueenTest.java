package chess.domain.chessPiece.pieceType;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {
    @Test
    void Queen_PieceColor_GenerateInstance() {
        assertThat(new Queen(PieceColor.BLACK)).isInstanceOf(Queen.class);
    }

    @Test
    void getName_ReturnName() {
        assertThat(new Queen(PieceColor.BLACK).getName()).isEqualTo("Q");
        assertThat(new Queen(PieceColor.WHITE).getName()).isEqualTo("q");
    }
}