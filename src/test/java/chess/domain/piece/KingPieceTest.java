package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingPieceTest {

    @ParameterizedTest
    @CsvSource({"44, 43", "44, 45", "44, 44", "44, 34", "44, 53", "44, 55", "44, 33", "44, 55"})
    @DisplayName("move 할 수 있는지 확인한다.")
    void canMove(String source, String target) {
        Piece king = new KingPiece(Color.BLACK);
        Position from = Position.create(source);
        Position to = Position.create(target);

        assertTrue(king.isMovable(from, to, false));
    }

    @Test
    @DisplayName("move 할 수 없는지 확인한다.")
    void cantMove() {
        Piece king = new KingPiece(Color.BLACK);
        Position from = Position.create("11");
        Position to = Position.create("13");

        assertFalse(king.isMovable(from, to, false));
    }

    @Test
    @DisplayName("King인지 확인한다.")
    void isKing() {
        Piece king = new KingPiece(Color.BLACK);
        assertTrue(king.isKing());
    }
}
