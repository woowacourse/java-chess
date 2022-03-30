package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.Color;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    void isBlack() {
        Piece blackPiece = new Rook(Color.BLACK);
        Piece whitePiece = new Rook(Color.WHITE);
        Piece nothing = new Empty();
        assertAll(
                () -> assertThat(blackPiece.isBlack()).isTrue(),
                () -> assertThat(whitePiece.isBlack()).isFalse(),
                () -> assertThat(nothing.isBlack()).isFalse()
        );
    }
}
