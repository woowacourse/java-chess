package chess.piece;

import static chess.domain.board.Board.*;
import static chess.domain.piece.Color.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Point;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Empty;
import chess.domain.piece.kind.Piece;
import chess.domain.piece.kind.Queen;

public class QueenTest {
    private Map<Point, Piece> board;

    @BeforeEach
    void setUp() {
        board = new HashMap<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board.put(Point.valueOf(i, j), PieceType.findPiece(i, j));
            }
        }
    }

    @DisplayName("Queen 생성")
    @Test
    public void create() {
        Queen blackQueen = new Queen(BLACK);
        assertEquals(blackQueen, board.get(Point.valueOf(0, 3)));
        Queen whiteQueen = new Queen(WHITE);
        assertEquals(whiteQueen, board.get(Point.valueOf(7, 3)));
    }

    @DisplayName("Queen 가능한 위치 확인")
    @Test
    void checkQueenPossibleMove() {
        Queen queen = new Queen(BLACK);

        assertDoesNotThrow(
            () -> queen.checkCorrectDistance(Point.valueOf(4, 4), Point.valueOf(5, 4), new Empty(NOTHING)));
        assertDoesNotThrow(
            () -> queen.checkCorrectDistance(Point.valueOf(4, 4), Point.valueOf(4, 5), new Empty(NOTHING)));
        assertDoesNotThrow(
            () -> queen.checkCorrectDistance(Point.valueOf(4, 4), Point.valueOf(3, 3), new Empty(NOTHING)));
        assertDoesNotThrow(
            () -> queen.checkCorrectDistance(Point.valueOf(4, 4), Point.valueOf(5, 5), new Empty(NOTHING)));
    }

    @DisplayName("Queen의 불가능한 위치 확인")
    @Test
    void checkQueenImpossibleMove() {
        Queen queen = new Queen(BLACK);

        assertThatThrownBy(
            () -> queen.checkCorrectDirection(Point.valueOf(4, 4).makeDirection(Point.valueOf(2, 3))))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(
            () -> queen.checkCorrectDirection(Point.valueOf(4, 4).makeDirection(Point.valueOf(6, 5))))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(
            () -> queen.checkCorrectDirection(Point.valueOf(4, 4).makeDirection(Point.valueOf(2, 5))))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(
            () -> queen.checkCorrectDirection(Point.valueOf(4, 4).makeDirection(Point.valueOf(6, 3))))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
