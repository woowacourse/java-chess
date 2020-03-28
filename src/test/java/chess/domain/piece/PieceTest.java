package chess.domain.piece;

import chess.domain.position.Position;
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
                Arguments.of(new WhitePawn('p', new Position(1, 2)), 'p'),
                Arguments.of(new BlackPawn('P', new Position(1, 7)), 'P'),
                Arguments.of(new Rook('r', new Position(1, 1)), 'r'),
                Arguments.of(new Rook('R', new Position(1, 8)), 'R'),
                Arguments.of(new Knight('n', new Position(2, 1)), 'n'),
                Arguments.of(new Knight('N', new Position(2, 8)), 'N'),
                Arguments.of(new Bishop('b', new Position(3, 1)), 'b'),
                Arguments.of(new Bishop('B', new Position(3, 8)), 'B'),
                Arguments.of(new Queen('q', new Position(4, 1)), 'q'),
                Arguments.of(new Queen('Q', new Position(4, 8)), 'Q'),
                Arguments.of(new King('k', new Position(5, 1)), 'k'),
                Arguments.of(new King('K', new Position(5, 8)), 'K')
        );
    }

    @DisplayName("각 말들의 처음 포지션 초기화")
    @ParameterizedTest
    @MethodSource("getCasesForPieceInitialPosition")
    void initializePiecePosition(Piece piece, Position expectedPosition) {
        assertThat(piece.getPosition()).isEqualTo(expectedPosition);
    }

    private static Stream<Arguments> getCasesForPieceInitialPosition() {
        return Stream.of(
                Arguments.of(new WhitePawn('p', new Position(1, 2)), new Position(1, 2)),
                Arguments.of(new BlackPawn('P', new Position(1, 7)), new Position(1, 7)),
                Arguments.of(new Rook('r', new Position(1, 1)), new Position(1, 1)),
                Arguments.of(new Rook('R', new Position(1, 8)), new Position(1, 8)),
                Arguments.of(new Knight('n', new Position(2, 1)), new Position(2, 1)),
                Arguments.of(new Knight('N', new Position(2, 8)), new Position(2, 8)),
                Arguments.of(new Bishop('b', new Position(3, 1)), new Position(3, 1)),
                Arguments.of(new Bishop('B', new Position(3, 8)), new Position(3, 8)),
                Arguments.of(new Queen('q', new Position(4, 1)), new Position(4, 1)),
                Arguments.of(new Queen('Q', new Position(4, 8)), new Position(4, 8)),
                Arguments.of(new King('k', new Position(5, 1)), new Position(5, 1)),
                Arguments.of(new King('K', new Position(5, 8)), new Position(5, 8))
        );
    }
}
