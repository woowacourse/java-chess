package chess.domain.piece.policy.move;

import chess.domain.board.Board;
import chess.domain.board.RunningBoard;
import chess.domain.piece.InitializedPawn;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.factory.PieceType;
import chess.domain.piece.position.Position;
import chess.domain.piece.state.piece.Initialized;
import chess.domain.piece.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class IsStayedTest {

    private IsStayed isStayed = new IsStayed();

    @ParameterizedTest
    @DisplayName("#canNotMove() : return boolean as to position 'from' and 'to' isEqual")
    @MethodSource({"getCasesForCanNotMove"})
    void canNotMove(Position from, Position to, boolean expected) {
        //given
        Initialized initialized = (InitializedPawn) PieceFactory.createInitializedPiece(PieceType.INITIALIZED_PAWN, from, Team.BLACK);
        Board board = RunningBoard.initiaize();

        boolean canNotMove = isStayed.canNotMove(initialized, to, board);
        assertThat(canNotMove).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForCanNotMove() {
        return Stream.of(
                Arguments.of(Position.of(1,1), Position.of(1,1), true),
                Arguments.of(Position.of(1,1), Position.of(1,2), false)
        );
    }
}