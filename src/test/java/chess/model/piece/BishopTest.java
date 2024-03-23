package chess.model.piece;

import static chess.model.material.Color.BLACK;
import static chess.model.material.Type.BISHOP;

import chess.model.Position;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BishopTest {

    @DisplayName("Bishop이 대각선 이동이면 움직일 수 있다")
    @ParameterizedTest
    @MethodSource("provideSourceAndTargetWithExpected")
    void bishopCanMove(Position source, Position target, boolean expected) {
        Piece piece = new Bishop(BISHOP, BLACK);
        boolean canMove = piece.canMove(source, target);
        Assertions.assertThat(canMove).isEqualTo(expected);
    }

    public static Stream<Arguments> provideSourceAndTargetWithExpected() {
        return Stream.of(
            Arguments.of(new Position(3, 3), new Position(7, 7), true),
            Arguments.of(new Position(3, 3), new Position(3, 7), false)
        );
    }
}
