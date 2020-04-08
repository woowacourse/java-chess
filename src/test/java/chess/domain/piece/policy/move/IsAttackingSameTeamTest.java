package chess.domain.piece.policy.move;

import chess.domain.board.Board;
import chess.domain.board.RunningBoard;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.factory.PieceType;
import chess.domain.piece.position.Position;
import chess.domain.piece.state.move.MoveType;
import chess.domain.piece.state.piece.Initialized;
import chess.domain.piece.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class IsAttackingSameTeamTest {

    private IsAttackingSameTeam isAttackingSameTeam = new IsAttackingSameTeam();

    @ParameterizedTest
    @DisplayName("#canNotMove : return boolean as to isHeading to team which piece in the position belong to")
    @MethodSource({"getCasesForCanNotMove"})
    void canNotMove(Team team, boolean expected) {
        Initialized initializedPiece = (Initialized) PieceFactory.createMovedPiece(PieceType.ROOK, Position.of(1,1), team, MoveType.MOVED);
        
        Board board = RunningBoard.initiaize();
        Position to = Position.of(1,7);
        boolean canNotMove = isAttackingSameTeam.canNotMove(initializedPiece, to, board);
        assertThat(canNotMove).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForCanNotMove() {
        return Stream.of(
                Arguments.of(Team.WHITE, false),
                Arguments.of(Team.BLACK, true)
        );
    }
}