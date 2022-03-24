package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.*;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingPieceTest {

    @ParameterizedTest
    @CsvSource({"d4, d3", "d4, d5","d4, e4","d4, c4","d4, e3","d4, e5","d4, c3","d4, c5"})
    @DisplayName("move 할 수 있는지 확인한다.")
    void canMove(String source, String target) {
        Piece king = new KingPiece(Color.BLACK);
        Position from = Position.create(source);
        Position to = Position.create(target);

        assertTrue(king.isMovable(from, to));
    }

    @Test
    @DisplayName("move 할 수 없는지 확인한다.")
    void cantMove() {
        Piece king = new KingPiece(Color.BLACK);
        Position from = Position.create("a1");
        Position to = Position.create("a3");

        assertFalse(king.isMovable(from, to));
    }
}