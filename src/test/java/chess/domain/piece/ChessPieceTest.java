package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessPieceTest {

    @Test
    @DisplayName("같은 팀이면 true 반환한다.")
    void isSamePositionTest() {
        ChessPiece piece = new Rook(Team.BLACK);
        boolean result = piece.isSameTeam(Team.BLACK);
        assertTrue(result);
    }

    @Test
    @DisplayName("다른 팀이면 false 반환한다.")
    void isNotSamePositionTest() {
        ChessPiece piece = new Rook(Team.BLACK);
        boolean result = piece.isSameTeam(Team.WHITE);
        assertFalse(result);
    }
}
