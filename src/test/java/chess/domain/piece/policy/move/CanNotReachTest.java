package chess.domain.piece.policy.move;


import chess.domain.piece.position.Position;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CanNotReachTest {
    private CanNotReach canNotReach = new CanNotReach(2);

//    @ParameterizedTest
//    @DisplayName("#canNotMove() : should return boolean measuring Position to against MAX_DISTANCE")
//    @MethodSource({"getCasesForCanNotMove"})
//    void canNotMove(Position from, Position to, boolean expected) {
//        PiecesState boardState = TestSquaresState.initialize();
//        boolean canNotMove = canNotReach.canNotMove(from, to, boardState);
//        assertThat(canNotMove).isEqualTo(expected);
//    }

    private static Stream<Arguments> getCasesForCanNotMove() {
        return Stream.of(
                Arguments.of(Position.of(1,1), Position.of(1,3), false),
                Arguments.of(Position.of(1,1), Position.of(2,2), false),
                Arguments.of(Position.of(1,1), Position.of(1,4), true)
        );
    }

}