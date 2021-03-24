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
import chess.domain.piece.kind.King;
import chess.domain.piece.kind.Piece;

public class KingTest {
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

    @DisplayName("king 생성")
    @Test
    public void create() {
        King king1 = new King(BLACK);
        assertEquals(king1, board.get(Point.valueOf(0, 4)));
        King king2 = new King(WHITE);
        assertEquals(king2, board.get(Point.valueOf(7, 4)));
    }

    @DisplayName("King의 가능한 거리 확인")
    @Test
    void checkKingPossibleMove() {
        King king = new King(BLACK);
        assertDoesNotThrow(() -> king.checkCorrectDistance(Point.valueOf(0, 0), Point.valueOf(1, 1), new Empty(WHITE)));
    }

    @DisplayName("King의 불가능한 거리 확인")
    @Test
    void checkKingImpossibleMove() {
        King king = new King(BLACK);

        assertThatThrownBy(() -> king.checkCorrectDistance(Point.valueOf(0, 0), Point.valueOf(2, 2), new Empty(WHITE)))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> king.checkCorrectDistance(Point.valueOf(0, 0), Point.valueOf(0, 2), new Empty(WHITE)))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> king.checkCorrectDistance(Point.valueOf(0, 0), Point.valueOf(0, 0), new Empty(WHITE)))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
