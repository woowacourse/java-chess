package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.pawn.WhitePawn;
import chess.domain.piece.straightmovablepiece.Rook;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PiecesTest {

    @Test
    @DisplayName("Pieces를 생성한다")
    void constructPieces() {
        final var pieces = new Pieces(List.of(new Rook(Color.WHITE), new WhitePawn()));

        assertThat(pieces).isInstanceOf(Pieces.class);
    }

    @Test
    @DisplayName("폰의 갯수를 계산한다")
    void getPawnCount() {
        final var pieces = new Pieces(List.of(new Rook(Color.WHITE), new WhitePawn(), new WhitePawn()));
        final long pawnCount = pieces.getPawnCount();

        assertThat(pawnCount).isEqualTo(2);
    }

    @Test
    @DisplayName("점수의 합을 계산한다.")
    void getSumOfScore() {
        final var pieces = new Pieces(List.of(new Rook(Color.WHITE), new WhitePawn(), new WhitePawn()));
        final double score = pieces.getSumOfScore();

        assertThat(score).isEqualTo(7);
    }
}
