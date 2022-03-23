package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.Color;
import chess.model.File;
import chess.model.Rank;
import chess.model.Square;
import chess.model.piece.Empty;
import chess.model.piece.Piece;
import chess.model.piece.Rook;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    void createPiece() {
        Piece rook = new Rook(Color.WHITE, new Square(File.A, Rank.ONE));
        assertThat(rook).isInstanceOf(Piece.class);
    }

    @Test
    void isBlack() {
        Piece blackPiece = new Rook(Color.BLACK, new Square(File.A, Rank.EIGHT));
        Piece whitePiece = new Rook(Color.WHITE, new Square(File.A, Rank.ONE));
        Piece nothing = new Empty(new Square(File.A, Rank.THREE));
        assertAll(
                () -> assertThat(blackPiece.isBlack()).isTrue(),
                () -> assertThat(whitePiece.isBlack()).isFalse(),
                () -> assertThat(nothing.isBlack()).isFalse()
        );
    }
}
