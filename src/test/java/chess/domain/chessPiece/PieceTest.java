package chess.domain.chessPiece;

import chess.domain.chessPiece.pieceType.King;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceTest {
    @Test
    void Piece_ColorAndType_GenerateInstance() {
        assertThat(new Piece(PieceColor.WHITE, King.getInstance())).isInstanceOf(Piece.class);
    }

    @Test
    void of_ColorAndType_ReturnInstance() {
        Piece piece1 = Piece.of(PieceColor.WHITE, King.getInstance());
        Piece piece2 = Piece.of(PieceColor.WHITE, King.getInstance());

        assertThat(piece1.equals(piece2)).isTrue();
        assertThat(piece1 == piece2).isTrue();
    }
}
