package chess.model.game;

import chess.model.board.ChessBoard;
import chess.model.board.ChessBoardInitializer;
import chess.model.piece.Side;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PositionEvaluationTest {

    @Test
    @DisplayName("체스판에 있는 기물에 대한 점수를 구한다.")
    void evaluate() {
        // given
        ChessBoard chessBoard = new ChessBoard(new ChessBoardInitializer().create());

        // when
        PositionEvaluation positionEvaluation = new PositionEvaluation(chessBoard.getBoard());
        double whiteEvaluation = positionEvaluation.getEvaluationBySide(Side.WHITE);
        double blackEvaluation = positionEvaluation.getEvaluationBySide(Side.BLACK);

        // then
        assertAll(
                () -> assertThat(whiteEvaluation).isEqualTo(38),
                () -> assertThat(blackEvaluation).isEqualTo(38)
        );
    }
}
