package chess.piece;

import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PieceTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("pieceProvider")
    void scoreTest(String name, Piece piece, double result) {
        assertThat(piece.getScore()).isEqualTo(result);
    }

    static Stream<Arguments> pieceProvider() {
        return Stream.of(
                Arguments.arguments("퀸 점수는", PieceType.QUEEN.createPiece(Camp.WHITE), 9),
                Arguments.arguments("룩 점수는", PieceType.ROOK.createPiece(Camp.WHITE), 5),
                Arguments.arguments("비숍 점수는", PieceType.BISHOP.createPiece(Camp.WHITE), 3),
                Arguments.arguments("나이트 점수는", PieceType.KNIGHT.createPiece(Camp.WHITE), 2.5),
                Arguments.arguments("폰 점수는", PieceType.PAWN.createPiece(Camp.WHITE), 1)
        );
    }
}
