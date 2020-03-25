package chess.domain.chessPiece;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessPieceTest {
    @Test
    void of_NameOfPieceType_ReturnInstance() {
        ChessPiece chessPiece1 = ChessPiece.of(PieceColor.WHITE.convertName(King.NAME));
        ChessPiece chessPiece2 = ChessPiece.of(PieceColor.WHITE.convertName(King.NAME));

        assertThat(chessPiece1.equals(chessPiece2)).isTrue();
        assertThat(chessPiece1 == chessPiece2).isTrue();
    }
}
