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

class QueenTest {
//    @ParameterizedTest
//    @DisplayName("#move() : should return Bishop as to Position 'from', 'to' and team")
//    @MethodSource({"getCasesForMove"})
//    void move(Position from, Position to, Team team, Piece expected) {
//        //todo: refac
//        Piece queen = null;
//
//        PiecesState boardState = TestSquaresState.initialize();
//        Piece exPiece = boardState.getPiece(to);
//        Piece moved = queen.move(to, exPiece);
//        assertThat(moved).isEqualTo(expected);
//    }
//
//    @Test
//    @DisplayName("#calculateScore() : should return score of Queen")
//    void calculateScore() {
//        //given
//        //todo: refac
//        Piece queen = null;
//        PiecesState boardState = TestSquaresState.initialize();
//        ;
//        //when
//        Score score = queen.calculateScore(boardState);
//        //then
//        //todo: refac
//        assertThat(score).isEqualTo(null);
//    }

    //todo: refac
    private static Stream<Arguments> getCasesForMove() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(Position.of(4, 2),
                        Position.of(4, 6),
                        team,
                        null),
                Arguments.of(Position.of(4, 2),
                        Position.of(1, 5),
                        team,
                        null),
                Arguments.of(Position.of(4, 2),
                        Position.of(8, 6),
                        team,
                        null),
                Arguments.of(Position.of(4, 6),
                        Position.of(4, 3),
                        team,
                        null)
        );
    }
}