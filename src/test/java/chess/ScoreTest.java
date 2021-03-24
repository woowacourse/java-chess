package chess;

import static chess.domain.piece.Color.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Score;
import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.domain.piece.kind.Bishop;
import chess.domain.piece.kind.King;
import chess.domain.piece.kind.Knight;
import chess.domain.piece.kind.Pawn;
import chess.domain.piece.kind.Piece;
import chess.domain.piece.kind.Queen;
import chess.domain.piece.kind.Rook;

public class ScoreTest {
    private Board board;

    @BeforeEach
    void setUp() {
        Map<Point, Piece> boardMap = new HashMap<>();
        boardMap.put(Point.valueOf(0, 0), new Pawn(BLACK, Point.valueOf(0, 0)));
        boardMap.put(Point.valueOf(1, 0), new Rook(BLACK, Point.valueOf(1, 0)));
        boardMap.put(Point.valueOf(1, 1), new Bishop(BLACK, Point.valueOf(1, 1)));
        boardMap.put(Point.valueOf(2, 0), new Knight(BLACK, Point.valueOf(2, 0)));
        boardMap.put(Point.valueOf(2, 1), new Queen(BLACK, Point.valueOf(2, 1)));
        boardMap.put(Point.valueOf(2, 2), new King(WHITE, Point.valueOf(2, 2)));
        board = new Board(boardMap);
    }

    @DisplayName("점수 생성")
    @Test
    void create() {
        Score score = new Score(board.getBoard(), Color.BLACK);
        assertEquals(20.5, score.getScore());
    }

    @DisplayName("초기 점수 확인")
    @Test
    void checkScore() {
        assertEquals(20.5, new Score(board.getBoard(), BLACK).getScore());
        assertEquals(0, new Score(board.getBoard(), WHITE).getScore());
    }
}
