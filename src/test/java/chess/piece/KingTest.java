package chess.piece;

import static chess.position.File.C;
import static chess.position.File.D;
import static chess.position.File.E;
import static chess.position.File.F;
import static chess.position.File.G;
import static chess.position.Rank.FIVE;
import static chess.position.Rank.FOUR;
import static chess.position.Rank.SIX;
import static chess.position.Rank.THREE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.piece.movementcondition.BaseMovementCondition;
import chess.position.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KingTest {

    @ParameterizedTest
    @MethodSource("provideInvalidMoveKing")
    @DisplayName("킹이 인접한 칸 외에 이동 시 예외 발생")
    void throwExceptionKingMoveOverOneSquare(Position from, Position to) {
        King king = new King(Color.BLACK);

        assertThat(king.identifyMovementCondition(from, to))
                .isEqualTo(BaseMovementCondition.IMPOSSIBLE);
    }

    private static Stream<Arguments> provideInvalidMoveKing() {
        return Stream.of(
                Arguments.of(new Position(E, FIVE), new Position(E, THREE)),
                Arguments.of(new Position(E, FIVE), new Position(G, THREE)),
                Arguments.of(new Position(E, FIVE), new Position(C, THREE)),
                Arguments.of(new Position(E, FIVE), new Position(D, THREE)),
                Arguments.of(new Position(E, FIVE), new Position(G, FIVE))
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidMoveKing")
    @DisplayName("킹은 인접한 칸으로만 이동할 수 있다.")
    void moveKingOneSquareToAdjacent(Position from, Position to) {
        King king = new King(Color.BLACK);

        assertThat(king.identifyMovementCondition(from, to))
                .isEqualTo(BaseMovementCondition.POSSIBLE);
    }

    private static Stream<Arguments> provideValidMoveKing() {
        return Stream.of(
                Arguments.of(new Position(E, FIVE), new Position(E, FOUR)),
                Arguments.of(new Position(E, FIVE), new Position(F, SIX)),
                Arguments.of(new Position(E, FIVE), new Position(F, FIVE)),
                Arguments.of(new Position(E, FIVE), new Position(F, FOUR)),
                Arguments.of(new Position(E, FIVE), new Position(D, FOUR)),
                Arguments.of(new Position(E, FIVE), new Position(D, FIVE)),
                Arguments.of(new Position(E, FIVE), new Position(D, SIX)),
                Arguments.of(new Position(E, FIVE), new Position(E, SIX))
        );
    }
}