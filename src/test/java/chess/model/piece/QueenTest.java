package chess.model.piece;

import static chess.model.Fixtures.D1;
import static chess.model.Fixtures.D5;
import static chess.model.Fixtures.F4;
import static chess.model.Fixtures.H1;
import static chess.model.material.Color.BLACK;
import static chess.model.material.Type.QUEEN;

import chess.model.position.Position;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class QueenTest {

    @DisplayName("Queen이 상하좌우 대각선 이동이면 움직일 수 있다")
    @ParameterizedTest
    @MethodSource("provideSourceAndTargetWithExpected")
    void queenCanMove(Position source, Position target, boolean expected) {
        Piece piece = new Queen(QUEEN, BLACK);
        boolean canMove = piece.canMove(source, target);
        Assertions.assertThat(canMove).isEqualTo(expected);
    }

    public static Stream<Arguments> provideSourceAndTargetWithExpected() {
        return Stream.of(
            Arguments.of(D5, D1, true),
            Arguments.of(D5, H1, true),
            Arguments.of(D5, F4, false)
        );
    }
}
