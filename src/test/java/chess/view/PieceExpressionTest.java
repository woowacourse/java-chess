package chess.view;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
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
                Arguments.of(King.colorOf(BLACK), "K"),
                Arguments.of(Queen.colorOf(BLACK), "Q"),
                Arguments.of(Rook.colorOf(BLACK), "R"),
                Arguments.of(Bishop.colorOf(BLACK), "B"),
                Arguments.of(Knight.colorOf(BLACK), "N"),
                Arguments.of(Pawn.colorOf(BLACK), "P"),
                Arguments.of(King.colorOf(WHITE), "k"),
                Arguments.of(Queen.colorOf(WHITE), "q"),
                Arguments.of(Rook.colorOf(WHITE), "r"),
                Arguments.of(Bishop.colorOf(WHITE), "b"),
                Arguments.of(Knight.colorOf(WHITE), "n"),
                Arguments.of(Pawn.colorOf(WHITE), "p"),
                Arguments.of(Empty.of(), ".")
        );
    }
}
