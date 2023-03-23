package chess.view;

import static org.assertj.core.api.Assertions.assertThat;

import chess.piece.sliding.Bishop;
import chess.piece.nonsliding.King;
import chess.piece.nonsliding.Knight;
import chess.piece.Piece;
import chess.piece.sliding.Rook;
import chess.piece.Side;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PieceNameTest {

    @ParameterizedTest
    @MethodSource("pieceNameOfCase")
    @DisplayName("기물 타입과 진영을 통해 해당 기물의 이름을 반환한다.")
    void pieceNameOf(Class<Piece> type, Side side, String expected) {
        // when
        final String name = PieceName.of(type, side);

        // then
        assertThat(name).isEqualTo(expected);
    }

    static Stream<Arguments> pieceNameOfCase() {
        return Stream.of(
                Arguments.of(King.class, Side.WHITE, "k"),
                Arguments.of(King.class, Side.BLACK, "K"),
                Arguments.of(Knight.class, Side.WHITE, "n"),
                Arguments.of(Bishop.class, Side.BLACK, "B"),
                Arguments.of(Rook.class, Side.BLACK, "R")
        );
    }
}
