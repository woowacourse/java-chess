package chess;

import chess.domain.Board;
import chess.domain.Point;
import chess.domain.Result;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.ChessGame.BOARD_SIZE;
import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(initializeBoard());
    }

    private Map<Point, Piece> initializeBoard() {
        Map<Point, Piece> initialBoard = new HashMap<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            initializeColumn(i, initialBoard);
        }
        return initialBoard;
    }

    private void initializeColumn(int i, Map<Point, Piece> initialBoard) {
        for (int j = 0; j < BOARD_SIZE; j++) {
            initialBoard.put(Point.of(i, j), PieceType.findPiece(i, j));
        }
    }

    @DisplayName("점수 생성 및 초기 점수 확인")
    @Test
    void create() {
        Result result = new Result(board.getBoard());
        assertEquals(38, result.getWhiteScore());
        assertEquals(38, result.getBlackScore());
    }

    @DisplayName("폰이 겹쳤을 때 0.5로 계산하는 점수 확인")
    @Test
    void checkPawnScore() {
        board.movePiece(Point.of("e2"), Point.of("e4"), WHITE);
        board.movePiece(Point.of("f7"), Point.of("f5"), BLACK);
        board.movePiece(Point.of("e4"), Point.of("f5"), WHITE);

        assertEquals(37, board.makeResult().getWhiteScore());
    }
}
