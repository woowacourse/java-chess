package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.Result;
import chess.domain.piece.team.Team;
import chess.domain.position.InitialColumn;
import chess.domain.position.MovingFlow;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {
    @ParameterizedTest
    @DisplayName("#movePiece() : should return Board with moved piece")
    @MethodSource({"getCasesForMovePiece"})
    void movePiece(MovingFlow movingFlow, Piece expected) {
        Board board = Board.initialize();
        Board movedBoard = board.movePiece(movingFlow);
        Piece piece = movedBoard.getPiece(movingFlow.getTo());
        assertThat(piece).isEqualTo(expected);
    }

    @Test
    @DisplayName("#getName() : should return Piece as to Position")
    void getPiece() {
        Board board = Board.initialize();
        Piece piece = board.getPiece(Position.of(1, 1));
        assertThat(piece).isNotNull();
    }

    @Test
    @DisplayName("#concludeResult() : should throw IllegalStateException")
    void concludeResult() {
        Score initialScore = Score.of(38);
        Board board = Board.initialize();
        Result result = board.concludeResult();
        assertThat(result.getWinner()).isEqualTo(Team.NOT_ASSIGNED.toString());
        assertThat(result.getBlackScore()).isEqualTo(initialScore.toString());
        assertThat(result.getWhiteScore()).isEqualTo(initialScore.toString());
    }

    private static Stream<Arguments> getCasesForMovePiece() {
        return Stream.of(
                Arguments.of(MovingFlow.of("a2", "a3"), PieceFactory.createInitializedPawn(Team.WHITE)),
                Arguments.of(MovingFlow.of("b1", "c3"), PieceFactory.createPieceWithInitialColumn(InitialColumn.KNIGHT, Team.WHITE))
        );
    }
}