package chess.model.piece;

import static chess.model.Fixtures.D5;
import static chess.model.Fixtures.E3;
import static chess.model.Fixtures.H3;
import static chess.model.material.Color.BLACK;
import static chess.model.material.Type.KNIGHT;

import chess.model.position.Position;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnightTest {

    @DisplayName("Knight이 L자 이동이면 움직일 수 있다")
    @ParameterizedTest
    @MethodSource("provideSourceAndTargetWithExpected")
    void knightCanMove(Position source, Position target, boolean expected) {
        Piece piece = new Knight(KNIGHT, BLACK);
        boolean canMove = piece.canMove(source, target);
        Assertions.assertThat(canMove).isEqualTo(expected);
    }

    public static Stream<Arguments> provideSourceAndTargetWithExpected() {
        return Stream.of(
            Arguments.of(D5, E3, true),
            Arguments.of(D5, H3, false)
        );
    }
}
