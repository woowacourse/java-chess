package chess.model.piece;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {
    @ParameterizedTest
    @MethodSource("providePieceWithSignature")
    void 기물_타입과_색깔에_맞는_시그니처를_반환한다(Piece piece, String signature) {
        assertThat(piece.getSignature()).isEqualTo(signature);
    }

    private static Stream<Arguments> providePieceWithSignature() {
        return Stream.of(
                Arguments.of(Piece.BLACK_BISHOP, "B"),
                Arguments.of(Piece.BLACK_ROOK, "R"),
                Arguments.of(Piece.BLACK_QUEEN, "Q"),
                Arguments.of(Piece.BLACK_KNIGHT, "N"),
                Arguments.of(Piece.BLACK_PAWN, "P"),
                Arguments.of(Piece.BLACK_KING, "K"),
                Arguments.of(Piece.WHITE_BISHOP, "b"),
                Arguments.of(Piece.WHITE_ROOK, "r"),
                Arguments.of(Piece.WHITE_QUEEN, "q"),
                Arguments.of(Piece.WHITE_KNIGHT, "n"),
                Arguments.of(Piece.WHITE_PAWN, "p"),
                Arguments.of(Piece.WHITE_KING, "k")
        );
    }
}
