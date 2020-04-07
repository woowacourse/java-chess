package chess.domain.piece.state;

import chess.domain.piece.factory.PieceType;
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
    void calculateScore(PieceType pieceType, Score expected) {
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
                Arguments.of(PieceType.ROOK, new Score(5)),
                Arguments.of(PieceType.KNIGHT, new Score(2.5)),
                Arguments.of(PieceType.BISHOP, new Score(3)),
                Arguments.of(PieceType.QUEEN, new Score(9)),
                Arguments.of(PieceType.KING, new Score(0))
        );
    }
}