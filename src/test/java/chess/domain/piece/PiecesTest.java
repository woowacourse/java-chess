package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PiecesTest {
    @Test
    @DisplayName("같은 열에 폰이 있을 때 0.5점으로 계산되는지 확인")
    void pawnCounting() {
        Pieces pieces = new Pieces(new Pawn(Color.BLACK, Position.from("d1")),
                new Pawn(Color.BLACK, Position.from("d2")),
                new Pawn(Color.BLACK, Position.from("d3")));
        assertThat(pieces.score(Color.BLACK)).isEqualTo(1.5);
    }
}