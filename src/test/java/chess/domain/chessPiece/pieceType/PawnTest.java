package chess.domain.chessPiece.pieceType;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
    @Test
    void Pawn_PieceColor_GenerateInstance() {
        assertThat(new Pawn(PieceColor.BLACK))
                .isInstanceOf(Pawn.class);
    }

    @Test
    void getName_ReturnName() {
        assertThat(new Pawn(PieceColor.BLACK)
                .getName()).isEqualTo("P");
        assertThat(new Pawn(PieceColor.WHITE)
                .getName()).isEqualTo("p");
    }
}