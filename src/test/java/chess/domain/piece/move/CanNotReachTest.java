package chess.domain.piece.move;

import chess.domain.UserInterface;
import chess.domain.board.Board;
import chess.domain.board.ChessBoard;
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

class CanNotReachTest {
    private CanNotReach canNotReach = new CanNotReach(2);
    private UserInterface userInterface = new Console();

    @ParameterizedTest
    @DisplayName("#canNotMove() : should return boolean measuring Position to against MAX_DISTANCE")
    @MethodSource({"getCasesForCanNotMove"})
    void canNotMove(Position from, Position to, boolean expected) {
        InitializedPawn initializedPawn = (InitializedPawn) PieceFactory.createPiece(InitializedPawn.class, from, Team.WHITE);
        Board board = ChessBoard.initiaize(userInterface);
        boolean canNotMove = canNotReach.canNotMove(initializedPawn, to, board);
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