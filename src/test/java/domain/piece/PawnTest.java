package domain.piece;

import domain.position.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PawnTest {
    @Test
    void createPawn() {
        Piece pawn = new Pawn("black");
        assertThat(pawn).isInstanceOf(Pawn.class);
    }

    @Test
    void whiteMove() {
        Piece pawn = new Pawn("white");
        boolean canMove = pawn.canMove(Position.from("a2"), Position.from("a3"));
        assertTrue(canMove);
    }

    @Test
    void blackMove() {
        Piece pawn = new Pawn("black");
        boolean canMove = pawn.canMove(Position.from("a2"), Position.from("a1"));
        assertTrue(canMove);
    }
}
