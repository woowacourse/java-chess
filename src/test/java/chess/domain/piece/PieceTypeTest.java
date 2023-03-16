package chess.domain.piece;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PieceTypeTest {

    @ParameterizedTest(name = "특정 체스말이 시작 위치에서 타겟 위치로 이동 가능한지 판단한다.")
    @MethodSource(value = "makePieceType")
    void canMove(final PieceType pieceType, final Position source, final Position target) {
        assertThat(pieceType.canMove(source, target))
                .isTrue();
    }

    private static Stream<Arguments> makePieceType() {
        return Stream.of(
                Arguments.of(PieceType.QUEEN, new Position(1, 1), new Position(7, 7))
                , Arguments.of(PieceType.ROOK, new Position(1, 1), new Position(7, 1))
                , Arguments.of(PieceType.KNIGHT, new Position(1, 1), new Position(3, 2))
                , Arguments.of(PieceType.PAWN, new Position(1, 1), new Position(2, 1))
                , Arguments.of(PieceType.BISHOP, new Position(1, 1), new Position(0, 0))
                , Arguments.of(PieceType.KING, new Position(1, 1), new Position(1, 0))
        );
    }
}
