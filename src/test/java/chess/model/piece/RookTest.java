package chess.model.piece;

import static chess.model.material.Color.BLACK;
import static chess.model.material.Type.ROOK;

import chess.model.Position;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RookTest {

    @DisplayName("Rook이 상하좌우 이동이면 움직일 수 있다")
    @ParameterizedTest
    @MethodSource("provideSourceAndTargetWithExpected")
    void rookCanMove(Position source, Position target, boolean expected) {
        Piece piece = new Rook(ROOK, BLACK);
        boolean canMove = piece.canMove(source, target);
        Assertions.assertThat(canMove).isEqualTo(expected);
    }

    public static Stream<Arguments> provideSourceAndTargetWithExpected() {
        return Stream.of(
            Arguments.of(new Position(3, 3), new Position(3, 7), true),
            Arguments.of(new Position(3, 3), new Position(7, 7), false)
        );
    }
}
