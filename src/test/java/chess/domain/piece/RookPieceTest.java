package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookPieceTest {

    @ParameterizedTest
    @CsvSource({"d4, d5", "d4, d2", "d4, a4", "d4, h4"})
    @DisplayName("move 할 수 있는지 확인한다.")
    void canMove(String source, String target) {
        Piece rook = new RookPiece(Color.BLACK);
        Position from = Position.create(source);
        Position to = Position.create(target);

        assertTrue(rook.isMovable(from, to, false));
    }

    @Test
    @DisplayName("move 할 수 없는지 확인한다.")
    void cantMove() {
        Piece rook = new RookPiece(Color.BLACK);
        Position from = Position.create("a1");
        Position to = Position.create("c3");

        assertFalse(rook.isMovable(from, to, false));
    }

}
