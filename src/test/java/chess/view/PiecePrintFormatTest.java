package chess.view;

import chess.domain.piece.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PiecePrintFormatTest {
    @Test
    void convert_test() {
        final String kingName = PiecePrintFormat.convert(new King());
        assertThat(kingName).isEqualTo("K");

        final String queenName = PiecePrintFormat.convert(new Queen());
        assertThat(queenName).isEqualTo("Q");

        final String rookName = PiecePrintFormat.convert(new Rook());
        assertThat(rookName).isEqualTo("R");

        final String knightName = PiecePrintFormat.convert(new Knight());
        assertThat(knightName).isEqualTo("N");

        final String bishopName = PiecePrintFormat.convert(new Bishop());
        assertThat(bishopName).isEqualTo("B");

        final String blackPawnName = PiecePrintFormat.convert(new Pawn(-1));
        assertThat(blackPawnName).isEqualTo("P");

        final String whitePawnName = PiecePrintFormat.convert(new Pawn(1));
        assertThat(whitePawnName).isEqualTo("p");
    }
}
