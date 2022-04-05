package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.*;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenPieceTest {

    @ParameterizedTest
    @CsvSource({"d4, d5", "d4, d2", "d4, g4", "e4, a4", "d4, f6", "d4, a7", "d5, h1", "f6, a1"})
    @DisplayName("move 할 수 있는지 확인한다.")
    void canMove(String source, String target) {
        Piece queen = new QueenPiece(Color.BLACK);
        Position from = Position.from(source);
        Position to = Position.from(target);

        assertTrue(queen.isRightMovement(from, to, false));
    }

    @Test
    @DisplayName("move 할 수 없는지 확인한다.")
    void cantMove() {
        Piece queen = new QueenPiece(Color.BLACK);
        Position from = Position.from("a1");
        Position to = Position.from("c2");

        assertFalse(queen.isRightMovement(from, to, false));
    }
}
