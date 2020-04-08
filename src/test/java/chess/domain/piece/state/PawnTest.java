package chess.domain.piece.state;

import chess.domain.board.Board;
import chess.domain.board.RunningBoard;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.factory.PieceType;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.piece.Initialized;
import chess.domain.piece.team.Team;
import chess.domain.ui.UserInterface;
import chess.ui.Console;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @ParameterizedTest
    @DisplayName("#calculateScore() : should return score as to board and position")
    @MethodSource({"getCasesForCalculateScore"})
    void calculateScore(Position position, Score expected) {
        //given
        Initialized pawn = (Initialized) PieceFactory.createInitializedPiece(PieceType.INITIALIZED_PAWN, position, Team.WHITE);
        Board board = RunningBoard.initiaize();
        //when
        Score score = pawn.calculateScore(board);
        //then
        assertThat(score).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForCalculateScore() {
        return Stream.of(
                Arguments.of(Position.of(1,2), new Score(1)),
                Arguments.of(Position.of(2,3), new Score(0.5))
        );
    }
}