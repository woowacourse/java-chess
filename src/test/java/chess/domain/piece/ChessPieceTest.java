package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessMen;
import chess.domain.Team;
import java.util.List;
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

    @Test
    @DisplayName("아군이 target 위치에 존재하는 경우 true 반환한다.")
    void myTeamExistsInTargetPositionTest() {
        ChessPiece piece = new Rook(Team.BLACK, new ChessBoardPosition('a', 3));
        ChessBoardPosition targetPosition = ChessBoardPosition.of("a5");
        List<ChessPiece> chessPieces = List.of(new Knight(Team.BLACK, ChessBoardPosition.of("a5")));
        ChessMen myTeam = new ChessMen(chessPieces);
        boolean result = piece.myTeamExistsInTargetPosition(targetPosition, myTeam);
        assertTrue(result);
    }
}
