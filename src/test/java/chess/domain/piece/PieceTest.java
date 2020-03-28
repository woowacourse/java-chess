package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceTest {
    @DisplayName("체스 말 각각의 표식")
    @ParameterizedTest
    @MethodSource("getCasesForPieceRepresentation")
    void representationTest(Piece piece, char expectedRepresentation) {
        assertThat(piece.getRepresentation()).isEqualTo(expectedRepresentation);
    }

    private static Stream<Arguments> getCasesForPieceRepresentation() {
        return Stream.of(
                Arguments.of(new Pawn('p'), 'p'),
                Arguments.of(new Pawn('P'), 'P'),
                Arguments.of(new Rook('r'), 'r'),
                Arguments.of(new Rook('R'), 'R'),
                Arguments.of(new Knight('n'), 'n'),
                Arguments.of(new Knight('N'), 'N'),
                Arguments.of(new Bishop('b'), 'b'),
                Arguments.of(new Bishop('B'), 'B'),
                Arguments.of(new King('k'), 'k'),
                Arguments.of(new King('K'), 'K'),
                Arguments.of(new Queen('q'), 'q'),
                Arguments.of(new Queen('Q'), 'Q')
        );
    }
}
