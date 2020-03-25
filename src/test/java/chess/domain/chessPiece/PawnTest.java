package chess.domain.chessPiece;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
    @Test
    void Pawn_MovableStrategy_GenerateInstance() {
        assertThat(new Pawn(PieceColor.BLACK)).isInstanceOf(Pawn.class);
    }

    @Test
    void getName_ReturnName() {
        String expectedWhite = "p";
        String expectedBlack = "P";

        assertThat(new Pawn(PieceColor.WHITE).getName()).isEqualTo(expectedWhite);
        assertThat(new Pawn(PieceColor.BLACK).getName()).isEqualTo(expectedBlack);
    }

}