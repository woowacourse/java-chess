package chess.domain.piece.state;

import chess.domain.UserInterface;
import chess.domain.board.Board;
import chess.domain.board.InitializedBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.bishop.Bishop;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.king.King;
import chess.domain.piece.knight.Knight;
import chess.domain.piece.pawn.InitializedPawn;
import chess.domain.piece.queen.Queen;
import chess.domain.piece.rook.Rook;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;
import chess.ui.Console;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NotPawnTest {

    @ParameterizedTest
    @DisplayName("#calculateScore() : should return score as PieceType")
    @MethodSource({"getCasesForCalculateScore"})
    void calculateScore(Class<? extends Piece> pieceType, Score expected) {
        //given
        //todo: check casting
        Initialized piece = (Initialized) PieceFactory.createPiece(pieceType, Position.of(5,5), Team.WHITE);
        UserInterface userInterface = new Console();
        Board board = InitializedBoard.initiaize(userInterface);
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
                Arguments.of(Queen.class, new Score(3)),
                Arguments.of(King.class, new Score(0))
                );
    }
}