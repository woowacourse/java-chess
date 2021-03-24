package chess;

import static chess.domain.piece.Color.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Score;
import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.piece.Color;

public class ScoreTest {
    @DisplayName("점수 생성")
    @Test
    void create() {
        Board board = new Board();
        Score score = new Score(board.getBoard(), Color.BLACK);
        assertEquals(38, score.getScore());
    }

    @DisplayName("초기 점수 확인")
    @Test
    void checkScore() {
        Board board = new Board();
        assertEquals(38, board.makeScore(WHITE).getScore());
        assertEquals(38, board.makeScore(BLACK).getScore());
    }

    @DisplayName("폰이 겹쳤을 때 0.5로 계산하는 점수 확인")
    @Test
    void checkPawnScore() {
        Board board = new Board();
        board.movePiece(Point.of("e2"), Point.of("e4"), WHITE);
        board.movePiece(Point.of("f7"), Point.of("f5"), BLACK);
        board.movePiece(Point.of("e4"), Point.of("f5"), WHITE);

        assertEquals(37, board.makeScore(WHITE).getScore());
    }
}
