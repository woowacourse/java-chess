package chess.view;

import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

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
                Arguments.of(King.of(BLACK), "K"),
                Arguments.of(Queen.of(BLACK), "Q"),
                Arguments.of(Rook.of(BLACK), "R"),
                Arguments.of(Bishop.of(BLACK), "B"),
                Arguments.of(Knight.of(BLACK), "N"),
                Arguments.of(Pawn.of(BLACK), "P"),
                Arguments.of(King.of(WHITE), "k"),
                Arguments.of(Queen.of(WHITE), "q"),
                Arguments.of(Rook.of(WHITE), "r"),
                Arguments.of(Bishop.of(WHITE), "b"),
                Arguments.of(Knight.of(WHITE), "n"),
                Arguments.of(Pawn.of(WHITE), "p"),
                Arguments.of(EmptyPiece.of(), ".")
        );
    }
}
