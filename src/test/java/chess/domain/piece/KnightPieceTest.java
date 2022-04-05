package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightPieceTest {

    @ParameterizedTest
    @CsvSource({"d4, f5", "d4, f3", "d4, b3", "d4, b5", "d4, c6", "d4, e6", "d4, e2", "d4, c2"})
    @DisplayName("move 할 수 있는지 확인한다.")
    void canMove(String source, String target) {
        Piece knight = new KnightPiece(Color.BLACK);
        Position from = Position.from(source);
        Position to = Position.from(target);

        assertTrue(knight.isRightMovement(from, to, false));
    }

    @ParameterizedTest
    @CsvSource({"a1, a3", "a1, c3"})
    @DisplayName("move 할 수 없는지 확인한다.")
    void cantMove(String source, String target) {
        Piece knight = new KnightPiece(Color.BLACK);
        Position from = Position.from(source);
        Position to = Position.from(target);

        assertFalse(knight.isRightMovement(from, to, false));
    }
}
