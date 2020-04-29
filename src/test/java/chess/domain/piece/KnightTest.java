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
class KnightTest {

//    @ParameterizedTest
//    @DisplayName("#move() : should return Bishop as to Position 'from', 'to' and team")
//    @MethodSource({"getCasesForMove"})
//    void move(Position from, Position to, Team team, Piece expected) {
//        Piece knight = null;
//
//        PiecesState boardState = TestSquaresState.initialize();
//        Piece exPiece = boardState.getPiece(to);
//
//        Piece moved = knight.move(to, exPiece);
//        assertThat(moved).isEqualTo(expected);
//    }
//
//    @Test
//    @DisplayName("#calculateScore() : should return score of Knight")
//    void calculateScore() {
//        //given
//        Piece knight = null;
//        PiecesState boardState = TestSquaresState.initialize();
//        //when
//        Score score = knight.calculateScore(boardState);
//        //then
////        assertThat(score).isEqualTo(PieceType.KNIGHT.getScore());
//    }

    private static Stream<Arguments> getCasesForMove() {
        return Stream.of(
                Arguments.of(Position.of(4, 4),
                        Position.of(5, 6),
                        Team.WHITE,
                        null),
                Arguments.of(Position.of(4, 4),
                        Position.of(6, 5),
                        Team.WHITE,
                        null),
                Arguments.of(Position.of(4, 4),
                        Position.of(6, 3),
                        Team.WHITE,
                        null),
                Arguments.of(Position.of(4, 4),
                        Position.of(5, 2),
                        Team.BLACK,
                        null),
                Arguments.of(Position.of(4, 4),
                        Position.of(3, 2),
                        Team.BLACK,
                        null),
                Arguments.of(Position.of(4, 4),
                        Position.of(2, 3),
                        Team.WHITE,
                        null),
                Arguments.of(Position.of(4, 4),
                        Position.of(2, 5),
                        Team.WHITE,
                        null),
                Arguments.of(Position.of(4, 4),
                        Position.of(3, 6),
                        Team.WHITE,
                        null)
        );
    }
}