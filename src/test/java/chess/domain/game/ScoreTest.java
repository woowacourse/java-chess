package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.PieceColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardFactory;

class ScoreTest {

    @Test
    @DisplayName("각 팀의 기물 점수들의 총합을 계산할 수 있다.")
    void calculateScore() {
        final ChessBoard chessBoard = new ChessBoard(ChessBoardFactory.initChessBoard());

        final Score whiteScore = Score.calculateScore(chessBoard, PieceColor.WHITE);
        final Score blackScore = Score.calculateScore(chessBoard, PieceColor.BLACK);

        assertAll(
                () -> assertThat(whiteScore.getScore() == 34.0),
                () -> assertThat(blackScore.getScore() == 34.0)
        );
    }
}
