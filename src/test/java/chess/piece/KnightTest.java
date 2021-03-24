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
import chess.domain.piece.kind.Knight;
import chess.domain.piece.kind.Piece;

public class KnightTest {
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

    @DisplayName("knight 생성")
    @Test
    public void create() {
        Knight knight1 = new Knight(BLACK);
        assertEquals(knight1, board.get(Point.valueOf(0, 1)));
        Knight knight2 = new Knight(BLACK);
        assertEquals(knight2, board.get(Point.valueOf(0, 6)));
        Knight knight3 = new Knight(WHITE);
        assertEquals(knight3, board.get(Point.valueOf(7, 1)));
        Knight knight4 = new Knight(WHITE);
        assertEquals(knight4, board.get(Point.valueOf(7, 6)));
    }

    @DisplayName("Knight의 가능한 거리 확인")
    @Test
    void checkKnightPossibleMove() {
        Knight knight = new Knight(BLACK);
        assertDoesNotThrow(
            () -> knight.checkCorrectDistance(Point.valueOf(4, 4), Point.valueOf(2, 5), new Empty(WHITE)));
        assertDoesNotThrow(
            () -> knight.checkCorrectDistance(Point.valueOf(4, 4), Point.valueOf(2, 3), new Empty(WHITE)));
        assertDoesNotThrow(
            () -> knight.checkCorrectDistance(Point.valueOf(4, 4), Point.valueOf(3, 6), new Empty(WHITE)));
        assertDoesNotThrow(
            () -> knight.checkCorrectDistance(Point.valueOf(4, 4), Point.valueOf(3, 2), new Empty(WHITE)));
        assertDoesNotThrow(
            () -> knight.checkCorrectDistance(Point.valueOf(4, 4), Point.valueOf(6, 5), new Empty(WHITE)));
        assertDoesNotThrow(
            () -> knight.checkCorrectDistance(Point.valueOf(4, 4), Point.valueOf(6, 3), new Empty(WHITE)));
        assertDoesNotThrow(
            () -> knight.checkCorrectDistance(Point.valueOf(4, 4), Point.valueOf(5, 6), new Empty(WHITE)));
        assertDoesNotThrow(
            () -> knight.checkCorrectDistance(Point.valueOf(4, 4), Point.valueOf(5, 2), new Empty(WHITE)));
    }

    @DisplayName("Knight의 불가능한 거리 확인")
    @Test
    void checkKnightImpossibleMove() {
        Knight knight = new Knight(BLACK);
        assertThatThrownBy(
            () -> knight.checkCorrectDistance(Point.valueOf(4, 4), Point.valueOf(9, 4), new Empty(WHITE)))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(
            () -> knight.checkCorrectDistance(Point.valueOf(0, 0), Point.valueOf(0, 2), new Empty(WHITE)))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(
            () -> knight.checkCorrectDistance(Point.valueOf(0, 0), Point.valueOf(0, 0), new Empty(WHITE)))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
