package chess.domain.piece.policy.move;

import static org.assertj.core.api.Assertions.assertThat;

class PawnIsDiagonalWithoutAttackTest {

    private PawnIsDiagonalWithoutAttack pawnIsDiagonalWithoutAttack = new PawnIsDiagonalWithoutAttack();

//    @ParameterizedTest
//    @DisplayName("#canNotMove : return boolean as to Position 'from', 'to', team and the Piece at the position")
//    @MethodSource({"getCasesForCanNotMove"})
//    void canNotMove(Position from, Position to, boolean expected) {
//        PiecesState boardState = TestSquaresState.initialize();
//        boolean canNotMove = pawnIsDiagonalWithoutAttack.canNotMove(from, to, boardState);
//        assertThat(canNotMove).isEqualTo(expected);
//    }
//
//    private static Stream<Arguments> getCasesForCanNotMove() {
//        return Stream.of(
//                Arguments.of(Position.of(1,2), Position.of(2,3), true),
//                Arguments.of(Position.of(1,2), Position.of(1,3), false)
//
//        );
//    }
}