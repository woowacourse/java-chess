package chess.domain.piece;

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

class MovedPawnTest {

    @ParameterizedTest
    @DisplayName("#move() : should return MovedPawn as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForMove"})
    void move(Position from, Position to, Team team, Piece expected) {
        Piece movedPawn = PieceFactory.createInitializedPiece(PieceType.MOVED_PAWN, from, team);

        PiecesState piecesState = TestPiecesState.initialize();
        Piece exPiece = piecesState.getPiece(to);
        Piece moved = movedPawn.move(to, exPiece);
        assertThat(moved).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("#canNotMove() : should return boolean as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForCanNotMove"})
    void canNotMove(Position from, Position to, Team team, boolean expected) {
        Piece movedPawn = PieceFactory.createInitializedPiece(PieceType.MOVED_PAWN, from, team);

        PiecesState piecesState = TestPiecesState.initialize();

        boolean actual = movedPawn.canNotMove(to, piecesState);
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForCanNotMove() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(Position.of(1, 2), Position.of(1, 4), team, true),
                Arguments.of(Position.of(1, 2), Position.of(3, 4), team, true)
        );
    }

    private static Stream<Arguments> getCasesForMove() {
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
    @DisplayName("#hasHindrance() : return boolean as to Position from, to and team")
    @MethodSource({"getCasesForHasHindrance"})
    void hasHindrance(Position from, Position to, Team team, boolean expected) {
        MovedPawn runningPawn = (MovedPawn) PieceFactory.createInitializedPiece(PieceType.MOVED_PAWN, from, team);
        PiecesState piecesState = TestPiecesState.initialize();
        boolean hasHindrance = runningPawn.hasHindrance(to, piecesState);
        assertThat(hasHindrance).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForHasHindrance() {
        return Stream.of(
                Arguments.of(Position.of(1, 3), Position.of(1, 4), Team.WHITE, false)
        );
    }
}