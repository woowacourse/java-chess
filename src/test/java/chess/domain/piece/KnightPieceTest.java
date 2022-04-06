package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightPieceTest {

    @ParameterizedTest
    @CsvSource({"44, 65", "44, 63", "44, 23", "44, 25", "44, 36", "44, 56", "44, 52", "44, 32"})
    @DisplayName("move 할 수 있는지 확인한다.")
    void canMove(String source, String target) {
        Piece knight = new KnightPiece(Color.BLACK);
        Position from = Position.create(source);
        Position to = Position.create(target);

        assertTrue(knight.isMovable(from, to, false));
    }

    @ParameterizedTest
    @CsvSource({"11, 13", "11, 33"})
    @DisplayName("move 할 수 없는지 확인한다.")
    void cantMove(String source, String target) {
        Piece knight = new KnightPiece(Color.BLACK);
        Position from = Position.create(source);
        Position to = Position.create(target);

        assertFalse(knight.isMovable(from, to, false));
    }

    @Test
    @DisplayName("Knight가 jump가능한지 확인한다.")
    void isJumpable() {
        Piece knight = new KnightPiece(Color.BLACK);
        assertTrue(knight.isJumpable());
    }
}
