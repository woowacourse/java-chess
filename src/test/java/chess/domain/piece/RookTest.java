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

class RookTest {

    private static final Position INITIAL_ROOK_POSITION = Position.of(1, 1);
    private static final Position CURRENT_ROOK_POSITION = Position.of(1, 3);

    @ParameterizedTest
    @DisplayName("#canNotMove() : should return boolean as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForCanNotMove"})
    void canNotMove(Position from, Position to, Team team, boolean expected) {
        Piece rook = new Rook(team);

        Pieces testPieces = TestPieces.initialize();
        Pieces pieces = testPieces.movePiece(INITIAL_ROOK_POSITION, CURRENT_ROOK_POSITION);

        boolean canNotMove = rook.canNotMove(from, to, pieces);
        assertThat(canNotMove).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForCanNotMove() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(CURRENT_ROOK_POSITION,
                        CURRENT_ROOK_POSITION,
                        team,
                        true),
                Arguments.of(CURRENT_ROOK_POSITION,
                        INITIAL_ROOK_POSITION,
                        team,
                        true),
                Arguments.of(CURRENT_ROOK_POSITION,
                        Position.of(1, 2),
                        team,
                        true),
                Arguments.of(CURRENT_ROOK_POSITION,
                        Position.of(2, 4),
                        team,
                        true),
                Arguments.of(CURRENT_ROOK_POSITION,
                        Position.of(1, 4),
                        team,
                        false),
                Arguments.of(CURRENT_ROOK_POSITION,
                        Position.of(1, 7),
                        team,
                        false)
        );
    }


//    @Test
//    @DisplayName("#calculateScore() : should return score of Rook")
//    void calculateScore() {
//        //given
//        Piece rook = null;
//        Pieces boardState = TestPieces.initialize();
//        //when
//        Score score = rook.calculateScore(boardState);
//        //then
//        assertThat(score).isEqualTo(null);
//    }
}