package chess.view;

import chess.domain.piece.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PieceNameConverterTest {
    @Test
    void convert_test() {
        final String kingName = PieceNameConverter.convert(new King());
        assertThat(kingName).isEqualTo("K");

        final String queenName = PieceNameConverter.convert(new Queen());
        assertThat(queenName).isEqualTo("Q");

        final String rookName = PieceNameConverter.convert(new Rook());
        assertThat(rookName).isEqualTo("R");

        final String knightName = PieceNameConverter.convert(new Knight());
        assertThat(knightName).isEqualTo("N");

        final String bishopName = PieceNameConverter.convert(new Bishop());
        assertThat(bishopName).isEqualTo("B");

        final String blackPawnName = PieceNameConverter.convert(new Pawn(-1));
        assertThat(blackPawnName).isEqualTo("P");

        final String whitePawnName = PieceNameConverter.convert(new Pawn(1));
        assertThat(whitePawnName).isEqualTo("p");
    }
}
