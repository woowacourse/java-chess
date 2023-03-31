package chess.domain;

import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static chess.domain.PieceType.*;
import static org.assertj.core.api.Assertions.assertThat;

class PieceTypeTest {
    @DisplayName("기물 타입과 색깔을 통해 기물 구현체를 반환한다.")
    @ParameterizedTest
    @MethodSource("newInstanceDummy")
    void newInstance(final Color color, final PieceType pieceType, final Piece expected) {
        // given & when
        final Piece piece = pieceType.newInstance(color);

        // then
        assertThat(piece).isEqualTo(expected);
    }

    static Stream<Arguments> newInstanceDummy() {
        return Stream.of(
                Arguments.arguments(WHITE, PAWN, Pawn.from(WHITE)),
                Arguments.arguments(WHITE, ROOK, Rook.from(WHITE)),
                Arguments.arguments(WHITE, KNIGHT, Knight.from(WHITE)),
                Arguments.arguments(WHITE, BISHOP, Bishop.from(WHITE)),
                Arguments.arguments(WHITE, QUEEN, Queen.from(WHITE)),
                Arguments.arguments(WHITE, KING, King.from(WHITE)),
                Arguments.arguments(WHITE, EMPTY, Empty.create()),
                Arguments.arguments(BLACK, PAWN, Pawn.from(BLACK)),
                Arguments.arguments(BLACK, ROOK, Rook.from(BLACK)),
                Arguments.arguments(BLACK, KNIGHT, Knight.from(BLACK)),
                Arguments.arguments(BLACK, BISHOP, Bishop.from(BLACK)),
                Arguments.arguments(BLACK, QUEEN, Queen.from(BLACK)),
                Arguments.arguments(BLACK, KING, King.from(BLACK)),
                Arguments.arguments(BLACK, EMPTY, Empty.create())
        );
    }
}
