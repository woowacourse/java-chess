package chess.domain.chessPiece;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {
    @Test
    void Rook_MovableStrategy_GenerateInstance() {
        assertThat(new Rook(PieceColor.BLACK)).isInstanceOf(Rook.class);
    }

    @Test
    void getName_ReturnName() {
        String expected = "R";

        assertThat(new Rook(PieceColor.BLACK).getName()).isEqualTo(expected);
    }
}