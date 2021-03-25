package chess;

import static chess.domain.board.Board.*;
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
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Piece;

public class ScoreTest {
    private Board board;

    @BeforeEach
    void setUp() {
        Map<Point, Piece> boardMap = new HashMap<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                boardMap.put(Point.valueOf(i, j), PieceType.findPiece(i, j));
            }
        }
        board = new Board(boardMap);
    }

    @DisplayName("점수 생성")
    @Test
    void create() {
        Score score = new Score(board.getBoard(), Color.BLACK);
        assertEquals(38, score.getScore());
    }

    @DisplayName("초기 점수 확인")
    @Test
    void checkScore() {
        assertEquals(38, new Score(board.getBoard(), BLACK).getScore());
        assertEquals(38, new Score(board.getBoard(), WHITE).getScore());
    }
}
