package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PieceTest {
    @ParameterizedTest
    @MethodSource("providePieceWithSignature")
    void 기물_타입과_색깔에_맞는_시그니처를_반환한다(Piece piece, String signature) {
        assertThat(piece.getSignature()).isEqualTo(signature);
    }

    private static Stream<Arguments> providePieceWithSignature() {
        return Stream.of(
                Arguments.of(Bishop.from(Color.BLACK), "B"),
                Arguments.of(Rook.from(Color.BLACK), "R"),
                Arguments.of(Queen.from(Color.BLACK), "Q"),
                Arguments.of(Knight.from(Color.BLACK), "N"),
                Arguments.of(Pawn.from(Color.BLACK), "P"),
                Arguments.of(King.from(Color.BLACK), "K"),
                Arguments.of(Bishop.from(Color.WHITE), "b"),
                Arguments.of(Rook.from(Color.WHITE), "r"),
                Arguments.of(Queen.from(Color.WHITE), "q"),
                Arguments.of(Knight.from(Color.WHITE), "n"),
                Arguments.of(Pawn.from(Color.WHITE), "p"),
                Arguments.of(King.from(Color.WHITE), "k")
        );
    }
}
