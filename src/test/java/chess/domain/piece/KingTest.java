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

class KingTest {
    private static final Position INITIAL_KING_POSITION = Position.of(5, 1);
    private static final Position CURRENT_KING_POSITION = Position.of(5, 3);

    @ParameterizedTest
    @DisplayName("#canNotMove() : should return boolean as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForCanNotMove"})
    void canNotMove(Position from, Position to, Team team, boolean expected) {
        Piece king = new King(team);

        Pieces testPieces = TestPieces.initialize();
        Pieces pieces = testPieces.movePiece(INITIAL_KING_POSITION, CURRENT_KING_POSITION);

        boolean canNotMove = king.canNotMove(from, to, pieces);
        assertThat(canNotMove).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForCanNotMove() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(CURRENT_KING_POSITION,
                        CURRENT_KING_POSITION,
                        team,
                        true),
                Arguments.of(CURRENT_KING_POSITION,
                        Position.of(5, 5),
                        team,
                        true),
                Arguments.of(CURRENT_KING_POSITION,
                        Position.of(5, 2),
                        team,
                        true),
                Arguments.of(CURRENT_KING_POSITION,
                        Position.of(5, 4),
                        team,
                        false)
        );
    }


//    @Test
//    @DisplayName("#calculateScore() : should return score of King")
//    void calculateScore() {
//        //given
//        Piece king = null;
//        Pieces boardState = TestPieces.initialize();
//        //when
//        Score score = king.calculateScore(boardState);
//        //then
//        assertThat(score).isEqualTo(null);
//    }
}