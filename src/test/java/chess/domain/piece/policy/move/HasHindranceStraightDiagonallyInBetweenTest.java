package chess.domain.piece.policy.move;

import chess.domain.piece.state.Pieces;
import chess.domain.piece.state.TestPieces;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class HasHindranceStraightDiagonallyInBetweenTest {
    private final HasHindranceStraightDiagonallyInBetween hasHindranceStraightDiagonallyInBetween = new HasHindranceStraightDiagonallyInBetween();

    @ParameterizedTest
    @DisplayName("#canNotMove() : should return boolean measuring Position to against MAX_DISTANCE")
    @MethodSource({"getCasesForCanNotMove"})
    void canNotMove(Position from, Position to, boolean expected) {
        Pieces pieces = TestPieces.initialize();
        boolean canNotMove = hasHindranceStraightDiagonallyInBetween.canNotMove(from, to, pieces);
        assertThat(canNotMove).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForCanNotMove() {
        return Stream.of(
                Arguments.of(Position.of(1,2), Position.of(3,4), false),
                Arguments.of(Position.of(1,1), Position.of(3,3), true)
        );
    }
}