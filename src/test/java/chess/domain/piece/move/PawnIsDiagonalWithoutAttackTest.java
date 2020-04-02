package chess.domain.piece.move;

import chess.domain.UserInterface;
import chess.domain.board.Board;
import chess.domain.board.InitializedBoard;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.pawn.InitializedPawn;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;
import chess.ui.Console;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PawnIsDiagonalWithoutAttackTest {

    private PawnIsDiagonalWithoutAttack pawnIsDiagonalWithoutAttack = new PawnIsDiagonalWithoutAttack();
    private UserInterface userInterface = new Console();

    @ParameterizedTest
    @DisplayName("#canNotMove : return boolean as to Position 'from', 'to', team and the Piece at the position")
    @MethodSource({"getCasesForCanNotMove"})
    void canNotMove(Team team, Position from, Position to, boolean expected) {
        InitializedPawn initializedPawn = (InitializedPawn) PieceFactory.createPiece(InitializedPawn.class, from, team);


        Board board = InitializedBoard.initiaize(userInterface);
        boolean canNotMove = pawnIsDiagonalWithoutAttack.canNotMove(initializedPawn, to, board);
        assertThat(canNotMove).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForCanNotMove() {
        return Stream.of(
                Arguments.of(Team.WHITE, Position.of(1,2), Position.of(2,3), true),
                Arguments.of(Team.WHITE, Position.of(1,1), Position.of(2,2), true),
                Arguments.of(Team.WHITE, Position.of(1,6), Position.of(2,7), false),
                Arguments.of(Team.WHITE, Position.of(1,2), Position.of(1,3), false)

        );
    }
}