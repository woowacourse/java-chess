package chess.domain.piece.policy.move;

import chess.domain.piece.position.Position;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class IsAttackingSameTeamTest {

    private IsAttackingSameTeam isAttackingSameTeam = new IsAttackingSameTeam();

//    @ParameterizedTest
//    @DisplayName("#canNotMove : return boolean as to isHeading to team which piece in the position belong to")
//    @MethodSource({"getCasesForCanNotMove"})
//    void canNotMove(Position from, Position to, boolean expected) {
//        PiecesState boardState = TestSquaresState.initialize();
//        boolean canNotMove = isAttackingSameTeam.canNotMove(from, to, boardState);
//        assertThat(canNotMove).isEqualTo(expected);
//    }

    private static Stream<Arguments> getCasesForCanNotMove() {
        return Stream.of(
                Arguments.of(Position.of(1,1), Position.of(1,7), false),
                Arguments.of(Position.of(1,8), Position.of(1,7), true)
        );
    }
}