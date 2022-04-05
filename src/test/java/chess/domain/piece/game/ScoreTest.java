package chess.domain.piece.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static chess.domain.piece.PieceColor.BLACK;
import static chess.domain.piece.PieceColor.WHITE;

import chess.domain.game.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardFactory;

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
