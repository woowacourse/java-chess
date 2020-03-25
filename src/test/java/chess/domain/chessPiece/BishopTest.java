package chess.domain.chessPiece;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {
    @Test
    void Bishop_MovableStrategy_GenerateInstance() {
        assertThat(new Bishop(PieceColor.WHITE)).isInstanceOf(Bishop.class);
    }

    @Test
    void getName_ReturnName() {
        String expected = "B";

        assertThat(new Bishop(PieceColor.BLACK).getName()).isEqualTo(expected);
    }
}