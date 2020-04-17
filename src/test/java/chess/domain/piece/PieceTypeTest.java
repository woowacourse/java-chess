package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceTypeTest {
    @DisplayName("체스 말 각각의 표식")
    @ParameterizedTest
    @MethodSource("getCasesForPieceRepresentation")
    void representationTest(PieceType pieceType, String expectedRepresentation) {
        assertThat(pieceType.getRepresentation()).isEqualTo(expectedRepresentation);
    }

    private static Stream<Arguments> getCasesForPieceRepresentation() {
        return Stream.of(
                Arguments.of(PieceType.FIRST_WHITE_PAWN, "p"),
                Arguments.of(PieceType.FIRST_BLACK_PAWN, "P"),
                Arguments.of(PieceType.WHITE_PAWN, "p"),
                Arguments.of(PieceType.BLACK_PAWN, "P"),
                Arguments.of(PieceType.WHITE_ROOK, "r"),
                Arguments.of(PieceType.BLACK_ROOK, "R"),
                Arguments.of(PieceType.WHITE_KNIGHT, "n"),
                Arguments.of(PieceType.BLACK_KNIGHT, "N"),
                Arguments.of(PieceType.WHITE_BISHOP, "b"),
                Arguments.of(PieceType.BLACK_BISHOP, "B"),
                Arguments.of(PieceType.WHITE_QUEEN, "q"),
                Arguments.of(PieceType.BLACK_QUEEN, "Q"),
                Arguments.of(PieceType.WHITE_KING, "k"),
                Arguments.of(PieceType.BLACK_KING, "K")
        );
    }

    @DisplayName("체스 말 각각의 점수 확인")
    @ParameterizedTest
    @MethodSource("getCasesForPieceScore")
    void pieceScore(PieceType pieceType, double expectedScore) {
        assertThat(pieceType.getScore()).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> getCasesForPieceScore() {
        return Stream.of(
                Arguments.of(PieceType.FIRST_WHITE_PAWN, 1),
                Arguments.of(PieceType.FIRST_BLACK_PAWN, 1),
                Arguments.of(PieceType.WHITE_PAWN, 1),
                Arguments.of(PieceType.BLACK_PAWN, 1),
                Arguments.of(PieceType.WHITE_ROOK, 5),
                Arguments.of(PieceType.BLACK_ROOK, 5),
                Arguments.of(PieceType.WHITE_KNIGHT, 2.5),
                Arguments.of(PieceType.BLACK_KNIGHT, 2.5),
                Arguments.of(PieceType.WHITE_BISHOP, 3),
                Arguments.of(PieceType.BLACK_BISHOP, 3),
                Arguments.of(PieceType.WHITE_QUEEN, 9),
                Arguments.of(PieceType.BLACK_QUEEN, 9),
                Arguments.of(PieceType.WHITE_KING, 0),
                Arguments.of(PieceType.BLACK_KING, 0)
        );
    }
}
