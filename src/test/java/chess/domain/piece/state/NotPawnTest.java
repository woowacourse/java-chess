package chess.domain.piece.state;

import chess.domain.ui.UserInterface;
import chess.domain.board.Board;
import chess.domain.board.RunningBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.Bishop;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.piece.Initialized;
import chess.domain.piece.team.Team;
import chess.domain.piece.position.Position;
import chess.ui.Console;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class NotPawnTest {

    @ParameterizedTest
    @DisplayName("#calculateScore() : should return score as PieceType")
    @MethodSource({"getCasesForCalculateScore"})
    void calculateScore(Class<? extends Piece> pieceType, Score expected) {
        //given
        Initialized piece = (Initialized) PieceFactory.createInitializedPiece(pieceType, Position.of(5, 5), Team.WHITE);
        UserInterface userInterface = new Console();
        Board board = RunningBoard.initiaize(userInterface);
        //when
        Score score = piece.calculateScore(board);
        //then
        assertThat(score).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForCalculateScore() {
        return Stream.of(
                Arguments.of(Rook.class, new Score(5)),
                Arguments.of(Knight.class, new Score(2.5)),
                Arguments.of(Bishop.class, new Score(3)),
                Arguments.of(Queen.class, new Score(9)),
                Arguments.of(King.class, new Score(0))
        );
    }
}