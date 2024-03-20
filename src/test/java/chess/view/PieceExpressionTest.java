package chess.view;

import static chess.domain.piece.PieceType.BISHOP;
import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.KNIGHT;
import static chess.domain.piece.PieceType.PAWN;
import static chess.domain.piece.PieceType.QUEEN;
import static chess.domain.piece.PieceType.ROOK;
import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;
import static org.assertj.core.api.Assertions.*;

import chess.domain.piece.Piece;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PieceExpressionTest {

    @ParameterizedTest
    @MethodSource("pieceExpressions")
    @DisplayName("기물을 인자로 넣으면 각 기물을 나타내는 문자열을 반환한다")
    void mapPieceToExpression(Piece piece, String expectedExpression) {
        String actualExpression = PieceExpression.mapToExpression(piece);

        assertThat(actualExpression).isEqualTo(expectedExpression);
    }

    static Stream<Arguments> pieceExpressions() {
        return Stream.of(
                Arguments.of(Piece.of(KING, BLACK), "K"),
                Arguments.of(Piece.of(QUEEN, BLACK), "Q"),
                Arguments.of(Piece.of(ROOK, BLACK), "R"),
                Arguments.of(Piece.of(BISHOP, BLACK), "B"),
                Arguments.of(Piece.of(KNIGHT, BLACK), "N"),
                Arguments.of(Piece.of(PAWN, BLACK), "P"),
                Arguments.of(Piece.of(KING, WHITE), "k"),
                Arguments.of(Piece.of(QUEEN, WHITE), "q"),
                Arguments.of(Piece.of(ROOK, WHITE), "r"),
                Arguments.of(Piece.of(BISHOP, WHITE), "b"),
                Arguments.of(Piece.of(KNIGHT, WHITE), "n"),
                Arguments.of(Piece.of(PAWN, WHITE), "p"),
                Arguments.of(null, ".")
        );
    }
}
