package chess.domain.piece;

import chess.domain.camp.CampType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @ParameterizedTest(name = "체스말이 흰색 진영의 말이면 true, 아니면 false를 반환한다.")
    @MethodSource(value = "makePiece")
    void isWhiteCamp(final Piece piece, final boolean expected) {
        assertThat(piece.isWhiteCamp())
                .isEqualTo(expected);
    }

    private static Stream<Arguments> makePiece() {
        return Stream.of(
                Arguments.of(new Piece(PieceType.QUEEN, CampType.WHITE), true)
                , Arguments.of(new Piece(PieceType.PAWN, CampType.BLACK), false)
        );
    }
}
