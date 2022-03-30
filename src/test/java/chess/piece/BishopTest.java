package chess.piece;

import static chess.position.File.A;
import static chess.position.File.B;
import static chess.position.File.C;
import static chess.position.File.D;
import static chess.position.File.E;
import static chess.position.File.F;
import static chess.position.File.G;
import static chess.position.Rank.EIGHT;
import static chess.position.Rank.FIVE;
import static chess.position.Rank.ONE;
import static chess.position.Rank.SIX;
import static chess.position.Rank.THREE;
import static chess.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import chess.piece.movementcondition.BaseMovementCondition;
import chess.position.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BishopTest {

    @ParameterizedTest
    @MethodSource("provideInvalidMoveBishop")
    @DisplayName("비숍은 대각선외에는 움직일 수 없다.")
    void throwExceptionInvalidMoveBishop(Position from, Position to) {
        Bishop bishop = new Bishop(Color.BLACK);

        assertThat(bishop.identifyMovementCondition(from, to))
                .isEqualTo(BaseMovementCondition.IMPOSSIBLE);
    }

    private static Stream<Arguments> provideInvalidMoveBishop() {
        return Stream.of(
                Arguments.of(new Position(C, EIGHT), new Position(D, THREE)),
                Arguments.of(new Position(C, EIGHT), new Position(G, FIVE)),
                Arguments.of(new Position(C, ONE), new Position(B, FIVE)),
                Arguments.of(new Position(C, ONE), new Position(F, SIX))
        );
    }

    @ParameterizedTest
    @MethodSource("provideCrossMoveBishop")
    @DisplayName("비숍은 대각선으로 이동할 수 있다.")
    void moveCrossBishop(Position from, Position to) {
        Bishop bishop = new Bishop(Color.BLACK);

        assertThat(bishop.identifyMovementCondition(from, to))
                .isEqualTo(BaseMovementCondition.MUST_OBSTACLE_FREE);
    }

    private static Stream<Arguments> provideCrossMoveBishop() {
        return Stream.of(
                Arguments.of(new Position(C, EIGHT), new Position(F, FIVE)),
                Arguments.of(new Position(C, EIGHT), new Position(A, SIX)),
                Arguments.of(new Position(C, ONE), new Position(B, TWO)),
                Arguments.of(new Position(C, ONE), new Position(E, THREE))
        );
    }
}