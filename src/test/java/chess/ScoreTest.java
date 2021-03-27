package chess;

import chess.domain.Board;
import chess.domain.Point;
import chess.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreTest {
    @DisplayName("점수 생성 및 초기 점수 확인")
    @Test
    void create() {
        Board board = new Board();
        Score score = new Score(board.getBoard());
        assertEquals(38, score.getWhiteScore());
        assertEquals(38, score.getBlackScore());
    }

    @DisplayName("폰이 겹쳤을 때 0.5로 계산하는 점수 확인")
    @Test
    void checkPawnScore() {
        Board board = new Board();
        board.movePiece(Point.of("e2"), Point.of("e4"), WHITE);
        board.movePiece(Point.of("f7"), Point.of("f5"), BLACK);
        board.movePiece(Point.of("e4"), Point.of("f5"), WHITE);

        assertEquals(37, board.makeScore().getWhiteScore());
    }
}
