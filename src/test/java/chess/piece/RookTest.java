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
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Piece;
import chess.domain.piece.kind.Rook;

public class RookTest {
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

    @DisplayName("Rook 생성")
    @Test
    public void create() {
        Rook rook1 = new Rook(BLACK);
        assertEquals(rook1, board.get(Point.valueOf(0, 0)));
        Rook rook2 = new Rook(BLACK);
        assertEquals(rook2, board.get(Point.valueOf(0, 7)));
        Rook rook3 = new Rook(WHITE);
        assertEquals(rook3, board.get(Point.valueOf(7, 0)));
        Rook rook4 = new Rook(WHITE);
        assertEquals(rook4, board.get(Point.valueOf(7, 7)));
    }

    @DisplayName("Rook의 가능한 방향 확인")
    @Test
    void checkRookPossibleMove() {
        Rook rook = new Rook(BLACK);

        assertDoesNotThrow(() -> rook.checkCorrectDirection(Direction.NORTH));
        assertDoesNotThrow(() -> rook.checkCorrectDirection(Direction.SOUTH));
        assertDoesNotThrow(() -> rook.checkCorrectDirection(Direction.WEST));
        assertDoesNotThrow(() -> rook.checkCorrectDirection(Direction.EAST));
    }

    @DisplayName("Rook의 불가능한 방향 확인")
    @Test
    void checkRookImpossibleMove() {
        Rook rook = new Rook(BLACK);

        assertThatThrownBy(() -> rook.checkCorrectDirection(Direction.NORTH_WEST))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> rook.checkCorrectDirection(Direction.SOUTH_EAST))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> rook.checkCorrectDirection(Direction.SOUTH_WEST))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> rook.checkCorrectDirection(Direction.NORTH_EAST))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
