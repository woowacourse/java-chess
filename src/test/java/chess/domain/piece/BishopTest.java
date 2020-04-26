package chess.domain.piece;

import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.factory.PieceType;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.move.MoveType;
import chess.domain.piece.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @ParameterizedTest
    @DisplayName("#move() : should return Bishop as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForMove"})
    void move(Position from, Position to, Team team, Piece expected) {
        Piece bishop = PieceFactory.createInitializedPiece(PieceType.BISHOP, team);

        PiecesState piecesState = TestPiecesState.initialize();
        Piece exPiece = piecesState.getPiece(to);
        Piece moved = bishop.move(to, exPiece);
        assertThat(moved).isEqualTo(expected);
    }

//    @ParameterizedTest
//    @DisplayName("#hasHindrance() : return boolean as to Position from, to and team")
//    @MethodSource({"getCasesForHasHindrance"})
//    void hasHindrance(Position from, Position to, Team team, boolean expected) {
//        Bishop bishop = (Bishop) PieceFactory.createInitializedPiece(PieceType.BISHOP, from, team);
//        PiecesState piecesState = TestPiecesState.initialize();
//        boolean hasHindrance = bishop.hasHindrance(to, piecesState);
//        assertThat(hasHindrance).isEqualTo(expected);
//    }

    @Test
    @DisplayName("#calculateScore() : should return score of Bishop")
    void calculateScore() {
        //given
        Piece bishop = PieceFactory.createInitializedPiece(PieceType.BISHOP, Team.WHITE);
        PiecesState piecesState = TestPiecesState.initialize();
        //when
        Score score = bishop.calculateScore(piecesState);
        //then
        assertThat(score).isEqualTo(PieceType.BISHOP.getScore());
    }

    private static Stream<Arguments> getCasesForMove() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(Position.of(2, 2),
                        Position.of(6, 6),
                        team,
                        PieceFactory.createMovedPiece(PieceType.BISHOP, team, MoveType.MOVED)),
                Arguments.of(Position.of(2, 2),
                        Position.of(7, 7),
                        team,
                        PieceFactory.createMovedPiece(PieceType.BISHOP, team, MoveType.ATTACKED_SUBORDINATE)),
                Arguments.of(Position.of(2, 2),
                        Position.of(1, 3),
                        team,
                        PieceFactory.createMovedPiece(PieceType.BISHOP, team, MoveType.MOVED))
        );
    }



    private static Stream<Arguments> getCasesForHasHindrance() {
        return Stream.of(
                Arguments.of(Position.of(3, 2), Position.of(4, 4), Team.WHITE, true),
                Arguments.of(Position.of(3, 1), Position.of(5, 3), Team.WHITE, true),
                Arguments.of(Position.of(1, 3), Position.of(3, 1), Team.WHITE, true),
                Arguments.of(Position.of(5, 3), Position.of(3, 1), Team.WHITE, true),
                Arguments.of(Position.of(3, 1), Position.of(1, 3), Team.WHITE, true),
                Arguments.of(Position.of(2, 2), Position.of(6, 6), Team.WHITE, false)
        );
    }
}