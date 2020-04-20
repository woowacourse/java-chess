package chess.domain.board;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.factory.PieceType;
import chess.domain.piece.position.MovingFlow;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FinishedBoardTest {

    @Test
    @DisplayName("#movePiece() : should throw IllegalStateException")
    void movePiece() {
        Map<Position, Piece> pieces = new HashMap<>();
        MovingFlow movingFlow = MovingFlow.of("a1","a2");
        FinishedBoard finishedBoard = new FinishedBoard(pieces);
        assertThatThrownBy(() -> finishedBoard.movePiece(movingFlow))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(FinishedBoard.FINISHED_ERROR);
    }

    @Test
    void concludeResult() {
        Map<Position, Piece> pieces = new HashMap<>();
        Position position = Position.of(5, 1);
        Piece whiteKing = PieceFactory.createInitializedPiece(PieceType.KING, position, Team.WHITE);
        pieces.put(position, whiteKing);
        FinishedBoard finishedBoard = new FinishedBoard(pieces);

        Result result = finishedBoard.concludeResult();
        Result expected = new Result(Team.WHITE, new Score(0), Team.BLACK, new Score(0));

        assertThat(result).isEqualTo(expected);
    }
}