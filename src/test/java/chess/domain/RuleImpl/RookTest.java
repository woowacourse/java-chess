package chess.domain.RuleImpl;

import chess.domain.Piece;
import chess.domain.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RookTest {
    @Test
    void isValidMoveTest() {
        Position origin = Position.of("1", "a");
        Position target = Position.of("8", "a");
        Piece piece = Piece.of(Piece.Color.WHITE, Rook.getInstance());
        assertTrue(piece.isValidMove(origin, target));

    }
}