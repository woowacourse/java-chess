package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.*;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopPieceTest {

    @ParameterizedTest
    @CsvSource({"d4, e5", "d4, b6", "d4, g1", "e5, a1"})
    @DisplayName("move 할 수 있는지 확인한다.")
    void canMove(String source, String target) {
        Piece bishop = new BishopPiece(Color.BLACK);
        Position from = Position.create(source);
        Position to = Position.create(target);

        assertTrue(bishop.isRightMovement(from, to, false));
    }

    @Test
    @DisplayName("move 할 수 없는지 확인한다.")
    void cantMove() {
        Piece bishop = new BishopPiece(Color.BLACK);
        Position from = Position.create("a1");
        Position to = Position.create("a3");

        assertFalse(bishop.isRightMovement(from, to, false));
    }

}
