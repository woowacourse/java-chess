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

class IsDiagonalWithoutAttackTest {
    private IsDiagonalWithoutAttack isDiagonalWithoutAttack = new IsDiagonalWithoutAttack();

    @ParameterizedTest
    @DisplayName("#canNotMove : return boolean as to Position 'from', 'to', team and the Piece at the position")
    @MethodSource({"getCasesForCanNotMove"})
    void canNotMove(Position from, Position to, boolean expected) {
        PiecesState boardState = TestPiecesState.initialize();
        boolean canNotMove = isDiagonalWithoutAttack.canNotMove(from, to, boardState);
        assertThat(canNotMove).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForCanNotMove() {
        return Stream.of(
                Arguments.of(Position.of(1,2), Position.of(2,3), true),
                Arguments.of(Position.of(1,2), Position.of(6,7), false),
                Arguments.of(Position.of(1,2), Position.of(1,3), false)

        );
    }
}