package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PiecesTest {

    @Test
    @DisplayName("Pieces를 생성한다")
    void construct() {
        final var pieces = new Pieces(List.of(new Rook(Color.WHITE), new Pawn(Color.WHITE)));

        assertThat(pieces).isInstanceOf(Pieces.class);
    }

    @Test
    @DisplayName("폰의 갯수를 계산한다")
    void getPawnCount() {
        final var pieces = new Pieces(List.of(new Rook(Color.WHITE), new Pawn(Color.WHITE), new Pawn(Color.WHITE)));
        final long pawnCount = pieces.getPawnCount();

        assertThat(pawnCount).isEqualTo(2);
    }

    @Test
    @DisplayName("점수의 합을 계산한다.")
    void getSumOfScore() {
        final var pieces = new Pieces(List.of(new Rook(Color.WHITE), new Pawn(Color.WHITE), new Pawn(Color.WHITE)));
        final double score = pieces.getSumOfScore();

        assertThat(score).isEqualTo(7);
    }
}
