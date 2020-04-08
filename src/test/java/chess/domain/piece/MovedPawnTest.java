package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.RunningBoard;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.factory.PieceType;
import chess.domain.piece.position.Position;
import chess.domain.piece.state.move.MoveType;
import chess.domain.piece.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MovedPawnTest {

    @ParameterizedTest
    @DisplayName("#move() : should return MovedPawn as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForMoveSucceed"})
    void moveSucceed(Position from, Position to, Team team, Piece expected) {
        Piece movedPawn = PieceFactory.createInitializedPiece(PieceType.MOVED_PAWN, from, team);

        Board board = RunningBoard.initiaize();
        Piece moved = movedPawn.move(to, board);
        assertThat(moved).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForMoveSucceed() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(Position.of(1, 3),
                        Position.of(1, 4),
                        team,
                        PieceFactory.createMovedPiece(PieceType.MOVED_PAWN, Position.of(1, 4), team, MoveType.MOVED)),

                Arguments.of(Position.of(1, 6),
                        Position.of(2, 7),
                        team,
                        PieceFactory.createMovedPiece(PieceType.MOVED_PAWN, Position.of(2, 7), team, MoveType.ATTACKED_SUBORDINATE)),

                Arguments.of(Position.of(1, 4),
                        Position.of(1, 5),
                        team,
                        PieceFactory.createMovedPiece(PieceType.MOVED_PAWN, Position.of(1, 5), team, MoveType.MOVED))
        );
    }

    @ParameterizedTest
    @DisplayName("#move() : should throw IllegalArgumentException as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForMoveFail"})
    void moveFail(Position from, Position to, Team team) {
        Piece movedPawn = PieceFactory.createInitializedPiece(PieceType.MOVED_PAWN, from, team);

        Board board = RunningBoard.initiaize();

        assertThatThrownBy(() -> movedPawn.move(to, board))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> getCasesForMoveFail() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(Position.of(1, 3), Position.of(1, 5), team),
                Arguments.of(Position.of(1, 4), Position.of(1, 6), team),
                Arguments.of(Position.of(1, 4), Position.of(3, 5), team),
                Arguments.of(Position.of(1, 6), Position.of(1, 7), team)

        );
    }

    @ParameterizedTest
    @DisplayName("#hasHindrance() : return boolean as to Position from, to and team")
    @MethodSource({"getCasesForHasHindrance"})
    void hasHindrance(Position from, Position to, Team team, boolean expected) {
        MovedPawn runningPawn = (MovedPawn) PieceFactory.createInitializedPiece(PieceType.MOVED_PAWN, from, team);
        Board board = RunningBoard.initiaize();
        boolean hasHindrance = runningPawn.hasHindrance(to, board);
        assertThat(hasHindrance).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForHasHindrance() {
        return Stream.of(
                Arguments.of(Position.of(1, 3), Position.of(1, 4), Team.WHITE, false)
        );
    }
}