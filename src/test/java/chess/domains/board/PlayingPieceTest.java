package chess.domains.board;

import chess.domains.piece.Blank;
import chess.domains.piece.PieceColor;
import chess.domains.piece.Rook;
import chess.domains.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayingPieceTest {
    @DisplayName("위치에 있는 PlayingPiece가 Blank일 경우 true 반환")
    @Test
    void isBlank() {
        PlayingPiece blankPiece = new PlayingPiece(Position.ofPositionName("a3"), new Blank());
        assertTrue(blankPiece.isBlank());

        PlayingPiece rook = new PlayingPiece(Position.ofPositionName("a3"), new Rook(PieceColor.WHITE));
        assertFalse(rook.isBlank());
    }
}