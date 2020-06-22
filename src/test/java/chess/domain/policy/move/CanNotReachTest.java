package chess.domain.policy.move;


import chess.domain.piece.state.PiecesState;
import chess.domain.piece.state.TestPiecesState;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CanNotReachTest {
    private final CanNotReach canNotReach = new CanNotReach(2);

    @ParameterizedTest
    @DisplayName("#canNotMove() : should return boolean measuring Position to against MAX_DISTANCE")
    @MethodSource({"getCasesForCanNotMove"})
    void canNotMove(Position from, Position to, boolean expected) {
        PiecesState piecesState = TestPiecesState.initialize();
        boolean canNotMove = canNotReach.canNotMove(from, to, piecesState);
        assertThat(canNotMove).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForCanNotMove() {
        return Stream.of(
                Arguments.of(Position.of(1,1), Position.of(1,3), false),
                Arguments.of(Position.of(1,1), Position.of(2,2), false),
                Arguments.of(Position.of(1,1), Position.of(1,4), true)
        );
    }

}