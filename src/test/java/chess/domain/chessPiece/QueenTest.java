package chess.domain.chessPiece;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {
    @Test
    void Queen_MovableStrategy_GenerateInstance() {
        assertThat(new Queen(PieceColor.BLACK)).isInstanceOf(Queen.class);
    }

    @Test
    void getName_ReturnName() {
        String expected = "Q";

        assertThat(new Queen(PieceColor.BLACK).getName()).isEqualTo(expected);
    }
}