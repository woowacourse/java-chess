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

class KingTest {
//    @ParameterizedTest
//    @DisplayName("#move() : should return King as to Position 'from', 'to' and team")
//    @MethodSource({"getCasesForMove"})
//    void move(Position from, Position to, Team team, Piece expected) {
//        Piece king = null;
//
//        PiecesState boardState = TestSquaresState.initialize();
//        Piece exPiece = boardState.getPiece(to);
//        Piece moved = king.move(to, exPiece);
//        assertThat(moved).isEqualTo(expected);
//    }


//    @Test
//    @DisplayName("#calculateScore() : should return score of King")
//    void calculateScore() {
//        //given
//        Piece king = null;
//        PiecesState boardState = TestSquaresState.initialize();
//        //when
//        Score score = king.calculateScore(boardState);
//        //then
//        assertThat(score).isEqualTo(null);
//    }

    private static Stream<Arguments> getCasesForMove() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(Position.of(5, 4),
                        Position.of(5, 5),
                        team,
                        null),
                Arguments.of(Position.of(5, 4),
                        Position.of(6, 5),
                        team,
                        null),
                Arguments.of(Position.of(5, 4),
                        Position.of(6, 4),
                        team,
                        null),
                Arguments.of(Position.of(5, 4),
                        Position.of(6, 3),
                        team,
                        null),
                Arguments.of(Position.of(5, 4),
                        Position.of(5, 3),
                        team,
                        null),
                Arguments.of(Position.of(5, 4),
                        Position.of(4, 3),
                        team,
                        null),
                Arguments.of(Position.of(5, 4),
                        Position.of(4, 4),
                        team,
                        null),

                Arguments.of(Position.of(5, 4),
                        Position.of(4, 5),
                        team,
                        null)
        );
    }
}