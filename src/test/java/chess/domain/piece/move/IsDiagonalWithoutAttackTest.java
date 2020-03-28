package chess.domain.piece.move;

import chess.domain.board.Board;
import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.pawn.InitializedPawn;
import chess.domain.piece.state.Initialized;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class IsDiagonalWithoutAttackTest {

    private IsDiagonalWithoutAttack isDiagonalWithoutAttack = new IsDiagonalWithoutAttack();

    @ParameterizedTest
    @DisplayName("#canNotMove : return boolean as to Position 'from', 'to', team and the Piece at the position")
    @MethodSource({"getCasesForCanNotMove"})
    void canNotMove(Team team, Position from, Position to, boolean expected) {
        InitializedPawn initializedPawn = new InitializedPawn("testInitializedPawn", from, team, new ArrayList<>());


        Board board = ChessBoard.initiaize();
        boolean canNotMove = isDiagonalWithoutAttack.canNotMove(initializedPawn, to, board);
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