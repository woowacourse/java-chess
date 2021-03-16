package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PieceKindTest {

    @DisplayName("체스 말 이름 getter 테스트")
    @Test
    void getName_pieceName_NotNull() {
        PieceColor blackPieceColor = PieceColor.BLACK;
        PieceColor whitePieceColor = PieceColor.WHITE;

        String blackPieceSymbol = PieceKind.KING.getName(blackPieceColor);
        String whitePieceSymbol = PieceKind.BISHOP.getName(whitePieceColor);

        assertEquals(blackPieceSymbol, "K");
        assertEquals(whitePieceSymbol, "b");
    }
}