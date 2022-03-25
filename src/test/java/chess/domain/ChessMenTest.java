package chess.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.piece.ChessPiece;
import chess.domain.piece.Rook;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessMenTest {

    @Test
    @DisplayName("주어진 위치에 체스피스가 있으면 true 반환")
    void existChessPieceInPositionTest() {
        List<ChessPiece> chessPieces = List.of(new Rook(Team.WHITE, new ChessBoardPosition('a', 3)));
        ChessMen chessMen = new ChessMen(chessPieces);

        boolean result = chessMen.existChessPieceInPosition(ChessBoardPosition.of("a3"));
        assertTrue(result);
    }

    @Test
    @DisplayName("주어진 위치에 체스피스가 없으면 false 반환")
    void notExistChessPieceInPositionTest() {
        List<ChessPiece> chessPieces = List.of(new Rook(Team.WHITE, new ChessBoardPosition('a', 3)));
        ChessMen chessMen = new ChessMen(chessPieces);

        boolean result = chessMen.existChessPieceInPosition(ChessBoardPosition.of("b3"));
        assertFalse(result);
    }
}
