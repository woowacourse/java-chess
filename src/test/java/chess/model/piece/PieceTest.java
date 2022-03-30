package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.Color;
import chess.model.File;
import chess.model.Rank;
import chess.model.Square;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    void createPiece() {
        Piece rook = new Rook(Color.WHITE, Square.of(File.A, Rank.ONE));
        assertThat(rook).isInstanceOf(Piece.class);
    }

    @Test
    void isBlack() {
        Piece blackPiece = new Rook(Color.BLACK, Square.of(File.A, Rank.EIGHT));
        Piece whitePiece = new Rook(Color.WHITE, Square.of(File.A, Rank.ONE));
        Piece nothing = new Empty(Square.of(File.A, Rank.THREE));
        assertAll(
                () -> assertThat(blackPiece.isBlack()).isTrue(),
                () -> assertThat(whitePiece.isBlack()).isFalse(),
                () -> assertThat(nothing.isBlack()).isFalse()
        );
    }
}
