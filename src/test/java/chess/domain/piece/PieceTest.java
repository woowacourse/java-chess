package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    static Stream<Arguments> isSamePieceTypeArguments() {
        return Stream.of(
                Arguments.arguments(PieceType.BLACK_PAWN, PieceType.BLACK_PAWN, true),
                Arguments.arguments(PieceType.BLACK_KING, PieceType.BLACK_PAWN, false)
        );
    }

    @DisplayName("기물의 타입이 주어진 타입과 같은지 판별한다.")
    @ParameterizedTest
    @MethodSource("isSamePieceTypeArguments")
    void isSamePieceType(PieceType pieceType1, PieceType pieceType2, boolean expected) {
        // given
        Piece piece = new Piece(pieceType1);

        // when
        boolean result = piece.isType(pieceType2);

        // then
        assertThat(result).isEqualTo(expected);
    }

}