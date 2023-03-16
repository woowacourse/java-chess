package chess.piece;

import chess.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    @Test
    @DisplayName("isBlack() : 피스가 검정색인지 확인할 수 있다.")
    void test_isBlack() throws Exception {
        //given
        Piece blackPiece = new Piece(Color.BLACK, new Position(1, 1)) {};
        Piece whitePiece = new Piece(Color.WHITE, new Position(2, 1)) {};

        //when & then
        assertTrue(blackPiece.isBlack());
        assertFalse(whitePiece.isBlack());
    }
}
