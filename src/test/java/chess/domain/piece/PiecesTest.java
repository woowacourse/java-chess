package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PiecesTest {
    @Test
    void pawnCounting() {
        Pieces pieces = new Pieces(new Pawn(Color.BLACK, Position.from("d1")),
                new Pawn(Color.BLACK, Position.from("d2")),
                new Pawn(Color.BLACK, Position.from("d3")));
        assertThat(pieces.score(Color.BLACK)).isEqualTo(1.5);
    }
}