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
    private static final Position INITIAL_QUEEN_POSITION = Position.of(4, 1);
    private static final Position CURRENT_QUEEN_POSITION = Position.of(4, 3);

    @ParameterizedTest
    @DisplayName("#canNotMove() : should return boolean as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForCanNotMove"})
    void canNotMove(Position from, Position to, Team team, boolean expected) {
        Piece queen = new Queen(team);

        TestPiecesState testPiecesState = TestPiecesState.initialize();
        PiecesState piecesState = testPiecesState.movePiece(INITIAL_QUEEN_POSITION, CURRENT_QUEEN_POSITION);

        boolean canNotMove = queen.canNotMove(from, to, piecesState);
        assertThat(canNotMove).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForCanNotMove() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(CURRENT_QUEEN_POSITION,
                        CURRENT_QUEEN_POSITION,
                        team,
                        true),
                Arguments.of(CURRENT_QUEEN_POSITION,
                        INITIAL_QUEEN_POSITION,
                        team,
                        true),
                Arguments.of(CURRENT_QUEEN_POSITION,
                        Position.of(4, 2),
                        team,
                        true),
                Arguments.of(CURRENT_QUEEN_POSITION,
                        Position.of(4, 2),
                        team,
                        true),
                Arguments.of(CURRENT_QUEEN_POSITION,
                        Position.of(6, 4),
                        team,
                        true)
        );
    }

//    @Test
//    @DisplayName("#calculateScore() : should return score of Queen")
//    void calculateScore() {
//        //given
//        //todo: refac
//        Piece queen = null;
//        PiecesState boardState = TestPiecesState.initialize();
//        ;
//        //when
//        Score score = queen.calculateScore(boardState);
//        //then
//        //todo: refac
//        assertThat(score).isEqualTo(null);
//    }
}