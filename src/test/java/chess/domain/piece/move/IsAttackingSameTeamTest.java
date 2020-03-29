package chess.domain.piece.move;

import chess.domain.board.Board;
import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.state.Initialized;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class IsAttackingSameTeamTest {

    private IsAttackingSameTeam isAttackingSameTeam = new IsAttackingSameTeam();

    @ParameterizedTest
    @DisplayName("#canNotMove : return boolean as to isHeading to team which piece in the position belong to")
    @MethodSource({"getCasesForCanNotMove"})
    void canNotMove(Team team, boolean expected) {
        Initialized initializedPiece = new Initialized("testInitializedPiece", Position.of(1,1), team, new ArrayList<>()) {
            @Override
            public boolean hasHindrance(Position to, Board board) {
                return false;
            }

            @Override
            protected boolean canNotMove(Position to, Board board) {
                return false;

            }

            @Override
            public Piece move(Position to, Board board) {
                return null;
            }
        };
        
        Board board = ChessBoard.initiaize();
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