package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessPieceTest {

    @Test
    @DisplayName("같은 위치이면 true 반환한다.")
    void isSamePositionTest() {
        ChessPiece piece = new Rook(Team.BLACK, new ChessBoardPosition('a', 3));
        boolean result = piece.isSamePosition(new ChessBoardPosition('a', 3));
        assertTrue(result);
    }

    @Test
    @DisplayName("다른 위치이면 false 반환한다.")
    void isNotSamePositionTest() {
        ChessPiece piece = new Rook(Team.BLACK, new ChessBoardPosition('a', 3));
        boolean result = piece.isSamePosition(new ChessBoardPosition('a', 4));
        assertFalse(result);
    }
}
