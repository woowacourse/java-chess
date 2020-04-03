package chess.piece;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceTest {
    @Test
    void equalsTest() {
        Piece piece1 = new Pawn(Team.BLACK);
        Piece piece2 = new Pawn(Team.BLACK);
        assertThat(piece1 == piece2).isFalse();
    }
}
