package chess.domain.piece;

import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.factory.PieceType;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class InitializedPawnTest {
    @ParameterizedTest
    @DisplayName("#hasHindrance() : return boolean as to Position from, to and team")
    @MethodSource({"getCasesForHasHindrance"})
    void hasHindrance(Position from, Position to, Team team, boolean expected) {
        InitializedPawn initializedPawn = (InitializedPawn) PieceFactory.createInitializedPiece(PieceType.INITIALIZED_PAWN, from, team);
        //todo: check package-dependency
        PiecesState piecesState = TestPiecesState.initialize();
        boolean hasHindrance = initializedPawn.hasHindrance(to, piecesState);
        assertThat(hasHindrance).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("#move() : should return Piece as to team and Position 'to'")
    @MethodSource({"getCasesForMove"})
    void move(Team team, Position to) {
        Piece initializedPawn = PieceFactory.createInitializedPiece(PieceType.INITIALIZED_PAWN, Position.of(1, 2), team);

        PiecesState piecesState = TestPiecesState.initialize();
        Piece exPiece = piecesState.getPiece(to);

        Piece moved = initializedPawn.move(to, exPiece);
        assertThat(moved).isInstanceOf(MovedPawn.class);
    }

    @ParameterizedTest
    @DisplayName("#calculateScore() : should return score of pawn as to board and position")
    @MethodSource({"getCasesForCalculateScore"})
    void calculateScore(Position position, Score expected) {
        //given
        Piece pawn = PieceFactory.createInitializedPiece(PieceType.INITIALIZED_PAWN, position, Team.WHITE);
        PiecesState piecesState = TestPiecesState.initialize();
        //when
        Score score = pawn.calculateScore(piecesState);
        //then
        assertThat(score).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForCalculateScore() {
        return Stream.of(
                Arguments.of(Position.of(1,2), new Score(1)),
                Arguments.of(Position.of(2,3), new Score(0.5))
        );
    }

    private static Stream<Arguments> getCasesForHasHindrance() {
        return Stream.of(
                Arguments.of(Position.of(1, 2), Position.of(1, 3), Team.WHITE, false),
                Arguments.of(Position.of(1, 2), Position.of(1, 4), Team.WHITE, false),
                Arguments.of(Position.of(1, 2), Position.of(2, 3), Team.WHITE, false),
                Arguments.of(Position.of(1, 5), Position.of(1, 7), Team.WHITE, true),
                Arguments.of(Position.of(1, 6), Position.of(1, 7), Team.WHITE, true)
        );
    }

    private static Stream<Arguments> getCasesForMove() {
        return Stream.of(
                Arguments.of(Team.WHITE, Position.of(1, 3)),
                Arguments.of(Team.WHITE, Position.of(1, 4))
        );
    }
}