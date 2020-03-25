package chess.domain.chessPiece;

import chess.domain.chessPiece.pieceType.King;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessPieceTest {
    @Test
    void of_ColorAndType_ReturnInstance() {
        ChessPiece chessPiece1 = ChessPiece.of(PieceColor.WHITE, King.getInstance());
        ChessPiece chessPiece2 = ChessPiece.of(PieceColor.WHITE, King.getInstance());

        assertThat(chessPiece1.equals(chessPiece2)).isTrue();
        assertThat(chessPiece1 == chessPiece2).isTrue();
    }
}
