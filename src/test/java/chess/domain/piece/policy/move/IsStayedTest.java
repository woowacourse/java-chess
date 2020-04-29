package chess.domain.piece.policy.move;

import chess.domain.piece.position.Position;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class IsStayedTest {

    private IsStayed isStayed = new IsStayed();

//    @ParameterizedTest
//    @DisplayName("#canNotMove() : return boolean as to position 'from' and 'to' isEqual")
//    @MethodSource({"getCasesForCanNotMove"})
//    void canNotMove(Position from, Position to, boolean expected) {
//        PiecesState boardState = TestSquaresState.initialize();
//
//        boolean canNotMove = isStayed.canNotMove(from, to, boardState);
//        assertThat(canNotMove).isEqualTo(expected);
//    }

    private static Stream<Arguments> getCasesForCanNotMove() {
        return Stream.of(
                Arguments.of(Position.of(1,1), Position.of(1,1), true),
                Arguments.of(Position.of(1,1), Position.of(1,2), false)
        );
    }
}