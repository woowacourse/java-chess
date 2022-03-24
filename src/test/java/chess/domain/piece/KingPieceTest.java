package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.*;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingPieceTest {

    @Test
    @DisplayName("move 할 수 있는지 확인한다.")
    void canMove() {
        Piece king = new KingPiece(Color.BLACK);
        Position from = Position.create("d4");
        Position to = Position.create("d5");

        assertTrue(king.isMovable(from, to));
    }

}