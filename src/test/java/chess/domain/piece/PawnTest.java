package chess.domain.piece;

import chess.domain.piece.position.Position;
import chess.domain.piece.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
    private static final Position INITIAL_PAWN_POSITION_FIRST = Position.of(1, 2);
    private static final Position INITIAL_PAWN_POSITION_SECOND = Position.of(2, 2);
    private static final Position MOVED_PAWN_POSITION = Position.of(3, 3);
    private static final Position ENEMY_POSITION = Position.of(1, 3);

    @ParameterizedTest
    @DisplayName("#canNotMove() : should return boolean as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForCanNotMove"})
    void canNotMove(Position from, Position to, Team team, boolean expected) {
        //given
        PiecesState testPiecesState = TestPiecesState.initialize();
        testPiecesState = testPiecesState.movePiece(Position.of(1, 7), ENEMY_POSITION);
        PiecesState piecesState = testPiecesState.movePiece(Position.of(3,2), MOVED_PAWN_POSITION);
        Piece initializedPawn = new Pawn(team);
        //when
        boolean canNotMove = initializedPawn.canNotMove(from, to, piecesState);
        //then
        assertThat(canNotMove).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForCanNotMove() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(INITIAL_PAWN_POSITION_FIRST,
                        INITIAL_PAWN_POSITION_FIRST,
                        team,
                        true),
                Arguments.of(INITIAL_PAWN_POSITION_SECOND,
                        Position.of(2, 5),
                        team,
                        true),
                Arguments.of(INITIAL_PAWN_POSITION_FIRST,
                        Position.of(1, 4),
                        team,
                        true),
                Arguments.of(INITIAL_PAWN_POSITION_SECOND,
                        Position.of(3, 3),
                        team,
                        true),
                Arguments.of(INITIAL_PAWN_POSITION_FIRST,
                        INITIAL_PAWN_POSITION_SECOND,
                        team,
                        true),
                Arguments.of(INITIAL_PAWN_POSITION_FIRST,
                        ENEMY_POSITION,
                        team,
                        true),
                Arguments.of(MOVED_PAWN_POSITION,
                        Position.of(3, 5),
                        team,
                        true),

                Arguments.of(INITIAL_PAWN_POSITION_SECOND,
                        ENEMY_POSITION,
                        team,
                        false),
                Arguments.of(INITIAL_PAWN_POSITION_SECOND,
                        Position.of(2, 3),
                        team,
                        false),
                Arguments.of(INITIAL_PAWN_POSITION_SECOND,
                        Position.of(2, 4),
                        team,
                        false),
                Arguments.of(MOVED_PAWN_POSITION,
                        Position.of(3, 4),
                        team,
                        false)

        );
    }

//    @ParameterizedTest
//    @DisplayName("#calculateScore() : should return score of pawn as to board and position")
//    @MethodSource({"getCasesForCalculateScore"})
//    void calculateScore(Position position, Score expected) {
//        //given
//        Piece pawn = null;
//        PiecesState boardState = TestPiecesState.initialize();
//        //when
//        Score score = pawn.calculateScore(boardState);
//        //then
//        assertThat(score).isEqualTo(expected);
//    }
//
//    private static Stream<Arguments> getCasesForCalculateScore() {
//        return Stream.of(
//                Arguments.of(Position.of(1,2), new Score(1)),
//                Arguments.of(Position.of(2,3), new Score(0.5))
//        );
//    }
}