package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PieceTest {
    @ParameterizedTest
    @MethodSource("providePieceName")
    void 기물_타입과_색깔에_맞는_이름을_반환한다(Piece piece, String name) {
        assertThat(piece.getName()).isEqualTo(name);
    }

    private static Stream<Arguments> providePieceName() {
        return Stream.of(
                Arguments.of(Bishop.from(Color.BLACK), "BLACK_BISHOP"),
                Arguments.of(Rook.from(Color.BLACK), "BLACK_ROOK"),
                Arguments.of(Queen.from(Color.BLACK), "BLACK_QUEEN"),
                Arguments.of(Knight.from(Color.BLACK), "BLACK_KNIGHT"),
                Arguments.of(Pawn.from(Color.BLACK), "BLACK_PAWN"),
                Arguments.of(King.from(Color.BLACK), "BLACK_KING"),
                Arguments.of(Bishop.from(Color.WHITE), "WHITE_BISHOP"),
                Arguments.of(Rook.from(Color.WHITE), "WHITE_ROOK"),
                Arguments.of(Queen.from(Color.WHITE), "WHITE_QUEEN"),
                Arguments.of(Knight.from(Color.WHITE), "WHITE_KNIGHT"),
                Arguments.of(Pawn.from(Color.WHITE), "WHITE_PAWN"),
                Arguments.of(King.from(Color.WHITE), "WHITE_KING")
        );
    }
}
