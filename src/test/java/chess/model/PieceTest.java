package chess.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    void createPiece() {
        Piece rook = new Piece(Name.ROOK, Color.WHITE);
        assertThat(rook).isInstanceOf(Piece.class);
    }

    @Test
    void isBlack() {
        Piece blackPiece = new Piece(Name.ROOK, Color.BLACK);
        Piece whitePiece = new Piece(Name.ROOK, Color.WHITE);
        Piece nothing = new Piece(Name.NOTHING, Color.NOTHING);
        assertAll(
                () -> assertThat(blackPiece.isBlack()).isTrue(),
                () -> assertThat(whitePiece.isBlack()).isFalse(),
                () -> assertThat(nothing.isBlack()).isFalse()
        );
    }
}
