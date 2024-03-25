package chess.model.piece;

import chess.model.Position;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnightTest {

    @DisplayName("Knight이 L자 이동이면 canMove true를 반환하고, 아니면 false를 반환한다")
    @ParameterizedTest
    @MethodSource("provideSourceAndTargetWithExpected")
    void knightCanMove(Position source, Position target, boolean expected) {
        Piece piece = new Knight(PieceType.KNIGHT, Color.BLACK);
        boolean canMove = piece.canMove(source, target);
        Assertions.assertThat(canMove).isEqualTo(expected);
    }

    public static Stream<Arguments> provideSourceAndTargetWithExpected() {
        return Stream.of(
                Arguments.of(new Position(3, 3), new Position(5, 4), true),
                Arguments.of(new Position(3, 3), new Position(3, 7), false)
        );
    }
}
