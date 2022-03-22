package chess.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    void createPiece() {
        Piece rook = new Piece(Name.ROOK, Color.WHITE);
        assertThat(rook).isInstanceOf(Piece.class);
    }
}
