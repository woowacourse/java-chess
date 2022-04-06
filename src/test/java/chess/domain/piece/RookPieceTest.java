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
    @CsvSource({"44, 45", "44, 42", "44, 14", "44, 84"})
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
        Position from = Position.create("11");
        Position to = Position.create("33");

        assertFalse(rook.isMovable(from, to, false));
    }

}
