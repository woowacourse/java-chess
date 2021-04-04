package chess.view;

import chess.domain.piece.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PieceConverterTest {
    @Test
    void convert_test() {
        final String kingName = PieceConverter.convertToPieceName(new King());
        assertThat(kingName).isEqualTo("K");

        final String queenName = PieceConverter.convertToPieceName(new Queen());
        assertThat(queenName).isEqualTo("Q");

        final String rookName = PieceConverter.convertToPieceName(new Rook());
        assertThat(rookName).isEqualTo("R");

        final String knightName = PieceConverter.convertToPieceName(new Knight());
        assertThat(knightName).isEqualTo("N");

        final String bishopName = PieceConverter.convertToPieceName(new Bishop());
        assertThat(bishopName).isEqualTo("B");

        final String blackPawnName = PieceConverter.convertToPieceName(new Pawn(-1));
        assertThat(blackPawnName).isEqualTo("P");

        final String whitePawnName = PieceConverter.convertToPieceName(new Pawn(1));
        assertThat(whitePawnName).isEqualTo("p");
    }
}
