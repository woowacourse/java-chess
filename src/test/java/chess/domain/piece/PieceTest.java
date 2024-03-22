package chess.domain.piece;

import chess.domain.piece.type.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    static Stream<Arguments> isSamePieceTypeArguments() {
        return Stream.of(
                Arguments.arguments(PieceType.PAWN, true),
                Arguments.arguments(PieceType.KING, false)
        );
    }

    @DisplayName("기물의 타입이 주어진 타입과 같은지 판별한다.")
    @ParameterizedTest
    @MethodSource("isSamePieceTypeArguments")
    void isSamePieceType(PieceType pieceType, boolean expected) {
        // given
        Piece piece = new Pawn(PieceColor.BLACK);

        // when
        boolean result = piece.isType(pieceType);

        // then
        assertThat(result).isEqualTo(expected);
    }

}
