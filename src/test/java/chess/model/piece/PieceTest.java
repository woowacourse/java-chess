package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
                Arguments.of(King.from(Color.WHITE), "WHITE_KING"),
                Arguments.of(Empty.getInstance(), "NONE_NONE")
        );
    }

    @ParameterizedTest
    @MethodSource("providePieceDestination")
    void 도착지에_같은_색깔의_기물이_있으면_예외가_발생한다(Piece source, Piece destination) {
        assertThatThrownBy(() -> source.validateDestinationColor(destination))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> providePieceDestination() {
        return Stream.of(
                Arguments.of(Bishop.from(Color.BLACK), Bishop.from(Color.BLACK)),
                Arguments.of(Rook.from(Color.BLACK), Bishop.from(Color.BLACK)),
                Arguments.of(Queen.from(Color.BLACK), Bishop.from(Color.BLACK)),
                Arguments.of(Knight.from(Color.BLACK), Bishop.from(Color.BLACK)),
                Arguments.of(Pawn.from(Color.BLACK), Bishop.from(Color.BLACK)),
                Arguments.of(King.from(Color.BLACK), Bishop.from(Color.BLACK)),
                Arguments.of(Bishop.from(Color.WHITE), Bishop.from(Color.WHITE)),
                Arguments.of(Rook.from(Color.WHITE), Bishop.from(Color.WHITE)),
                Arguments.of(Queen.from(Color.WHITE), Bishop.from(Color.WHITE)),
                Arguments.of(Knight.from(Color.WHITE), Bishop.from(Color.WHITE)),
                Arguments.of(Pawn.from(Color.WHITE), Bishop.from(Color.WHITE)),
                Arguments.of(King.from(Color.WHITE), Bishop.from(Color.WHITE))
        );
    }
}
