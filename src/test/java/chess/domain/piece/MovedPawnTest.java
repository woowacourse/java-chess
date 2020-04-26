package chess.domain.piece;

import chess.domain.piece.position.Position;
import chess.domain.piece.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

//todo: refac
class MovedPawnTest {

    @ParameterizedTest
    @DisplayName("#move() : should return MovedPawn as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForMove"})
    void move(Position from, Position to, Team team, Piece expected) {
        Piece movedPawn = null;

        PiecesState piecesState = TestPiecesState.initialize();
        Piece exPiece = piecesState.getPiece(to);
        Piece moved = movedPawn.move(to, exPiece);
        assertThat(moved).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForMove() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(Position.of(1, 3),
                        Position.of(1, 4),
                        team,
                        null),

                Arguments.of(Position.of(1, 6),
                        Position.of(2, 7),
                        team,
                        null),

                Arguments.of(Position.of(1, 4),
                        Position.of(1, 5),
                        team,
                        null)
        );
    }

    @ParameterizedTest
    @DisplayName("#hasHindrance() : return boolean as to Position from, to and team")
    @MethodSource({"getCasesForHasHindrance"})
    void hasHindrance(Position from, Position to, Team team, boolean expected) {
        MovedPawn runningPawn = null;
        PiecesState piecesState = TestPiecesState.initialize();
        //todo: refac
//        runningPawn.hasHindrance(to, piecesState);
        boolean hasHindrance = expected;
        assertThat(hasHindrance).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForHasHindrance() {
        return Stream.of(
                Arguments.of(Position.of(1, 3), Position.of(1, 4), Team.WHITE, false)
        );
    }
}