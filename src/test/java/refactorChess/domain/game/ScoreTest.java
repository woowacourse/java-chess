package refactorChess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static refactorChess.domain.piece.PieceColor.BLACK;
import static refactorChess.domain.piece.PieceColor.WHITE;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import refactorChess.domain.board.ChessBoard;
import refactorChess.domain.board.ChessBoardFactory;

class ScoreTest {

    @Test
    @DisplayName("각 팀의 기물 점수들의 총합을 계산할 수 있다.")
    void calculateScore() {
        final ChessBoard chessBoard = new ChessBoard(ChessBoardFactory.initChessBoard());

        final Score whiteScore = Score.calculateScore(chessBoard, WHITE);
        final Score blackScore = Score.calculateScore(chessBoard, BLACK);

        assertAll(
                () -> assertThat(whiteScore.getScore() == 34.0),
                () -> assertThat(blackScore.getScore() == 34.0)
        );
    }
}
