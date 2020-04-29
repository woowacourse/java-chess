package chess.domain.piece;

import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

//todo: refac
class RookTest {

//    @ParameterizedTest
//    @DisplayName("#move() : should return Piece as to Position 'from' and 'to'")
//    @MethodSource({"getCasesForMove"})
//    void move(Position from, Position to, Team team, Piece expected) {
//        Piece rook = null;
//
//        PiecesState boardState = TestSquaresState.initialize();
//        Piece exPiece = boardState.getPiece(to);
//
//        Piece moved = rook.move(to, exPiece);
//        assertThat(moved).isEqualTo(expected);
//    }
//
//    @ParameterizedTest
//    @DisplayName("#hasHindrance() : return boolean as to Position from, to and team")
//    @MethodSource({"getCasesForHasHindrance"})
//    void hasHindrance(Position from, Position to, Team team, boolean expected) {
//        Rook rook = null;
//        PiecesState boardState = TestSquaresState.initialize();
//        //todo: refac
////        rook.hasHindrance(to, boardState);
//        boolean hasHindrance = expected;
//        assertThat(hasHindrance).isEqualTo(expected);
//    }
//
//    @Test
//    @DisplayName("#calculateScore() : should return score of Rook")
//    void calculateScore() {
//        //given
//        Piece rook = null;
//        PiecesState boardState = TestSquaresState.initialize();
//        //when
//        Score score = rook.calculateScore(boardState);
//        //then
//        assertThat(score).isEqualTo(null);
//    }
//
//    private static Stream<Arguments> getCasesForMove() {
//        Team team = Team.WHITE;
//        return Stream.of(
//                Arguments.of(Position.of(1, 2),
//                        Position.of(1, 6),
//                        team,
//                        null),
//                Arguments.of(Position.of(1, 3),
//                        Position.of(8, 3),
//                        team,
//                        null),
//                Arguments.of(Position.of(1, 3),
//                        Position.of(1, 7),
//                        team,
//                        null)
//        );
//    }

    private static Stream<Arguments> getCasesForHasHindrance() {
        return Stream.of(
                Arguments.of(Position.of(1, 1), Position.of(1, 8), Team.WHITE, true),
                Arguments.of(Position.of(1, 2), Position.of(1, 8), Team.WHITE, true),
                Arguments.of(Position.of(1, 1), Position.of(1, 2), Team.WHITE, false),
                Arguments.of(Position.of(1, 1), Position.of(2, 1), Team.WHITE, false),
                Arguments.of(Position.of(1, 2), Position.of(1, 7), Team.WHITE, false),
                Arguments.of(Position.of(1, 2), Position.of(1, 6), Team.WHITE, false)
        );
    }
}