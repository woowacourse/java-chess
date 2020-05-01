package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.piece.state.Pieces;
import chess.domain.piece.team.Team;
import chess.domain.piece.state.TestPieces;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    private static final Position INITIAL_KINGHT_POSITION = Position.of(2, 1);

    @ParameterizedTest
    @DisplayName("#canNotMove() : should return boolean as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForCanNotMove"})
    void canNotMove(Position from, Position to, Team team, boolean expected) {
        Piece knight = new Knight(team);

        Pieces pieces = TestPieces.initialize();

        boolean canNotMove = knight.canNotMove(from, to, pieces);
        assertThat(canNotMove).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForCanNotMove() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(INITIAL_KINGHT_POSITION,
                        INITIAL_KINGHT_POSITION,
                        team,
                        true),
                Arguments.of(INITIAL_KINGHT_POSITION,
                        Position.of(3, 5),
                        team,
                        true),
                Arguments.of(INITIAL_KINGHT_POSITION,
                        Position.of(4, 3),
                        team,
                        true),
                Arguments.of(INITIAL_KINGHT_POSITION,
                        Position.of(4, 2),
                        team,
                        true),
                Arguments.of(INITIAL_KINGHT_POSITION,
                        Position.of(3, 3),
                        team,
                        false)
        );
    }

//    @Test
//    @DisplayName("#calculateScore() : should return score of Knight")
//    void calculateScore() {
//        //given
//        Piece knight = null;
//        Pieces boardState = TestPieces.initialize();
//        //when
//        Score score = knight.calculateScore(boardState);
//        //then
////        assertThat(score).isEqualTo(PieceType.KNIGHT.getScore());
//    }
}