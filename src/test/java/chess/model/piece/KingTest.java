package chess.model.piece;

import static chess.model.piece.PieceType.BLACK_KING;

import chess.model.Position;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KingTest {

    @DisplayName("King이 상하좌우 대각선 1칸 이동이면 움직일 수 있다")
    @ParameterizedTest
    @MethodSource("provideSourceAndTargetWithExpected")
    void kingCanMove(Position source, Position target, boolean expected) {
        Piece piece = new King(BLACK_KING);
        boolean canMove = piece.canMove(source, target);
        Assertions.assertThat(canMove).isEqualTo(expected);
    }

    public static Stream<Arguments> provideSourceAndTargetWithExpected() {
        return Stream.of(
                Arguments.of(new Position(3, 3), new Position(3, 4), true),
                Arguments.of(new Position(3, 3), new Position(5, 4), false)
        );
    }
}
