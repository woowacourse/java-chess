package domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class pawnTest {
    @Test
    void createPawn() {
        Piece pawn = new Pawn("black");
        assertThat(pawn).isInstanceOf(Pawn.class);
    }

    @Test
    void whiteMove() {
        Piece pawn = new Pawn("white");
        boolean canMove = pawn.canMove(Arrays.asList("a","2"), Arrays.asList("a","3"));
        assertTrue(canMove);
    }
}
