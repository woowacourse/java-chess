package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopPieceTest {

    @ParameterizedTest
    @CsvSource({"44, 55", "44, 26", "44, 71", "55, 11"})
    @DisplayName("move 할 수 있는지 확인한다.")
    void canMove(String source, String target) {
        Piece bishop = new BishopPiece(Color.BLACK);
        Position from = Position.create(source);
        Position to = Position.create(target);

        assertTrue(bishop.isMovable(from, to, false));
    }

    @Test
    @DisplayName("move 할 수 없는지 확인한다.")
    void cantMove() {
        Piece bishop = new BishopPiece(Color.BLACK);
        Position from = Position.create("11");
        Position to = Position.create("13");

        assertFalse(bishop.isMovable(from, to, false));
    }

}
