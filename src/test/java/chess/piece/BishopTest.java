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
import chess.domain.piece.kind.Bishop;
import chess.domain.piece.kind.Piece;

public class BishopTest {
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

    @DisplayName("Bishop 생성")
    @Test
    public void create() {
        Bishop bishop1 = new Bishop(BLACK);
        assertEquals(bishop1, board.get(Point.valueOf(0, 2)));
        Bishop bishop2 = new Bishop(BLACK);
        assertEquals(bishop2, board.get(Point.valueOf(0, 5)));
        Bishop bishop3 = new Bishop(WHITE);
        assertEquals(bishop3, board.get(Point.valueOf(7, 2)));
        Bishop bishop4 = new Bishop(WHITE);
        assertEquals(bishop4, board.get(Point.valueOf(7, 5)));
    }

    @DisplayName("Bishop의 불가능한 방향 확인")
    @Test
    void checkBishopPossibleMove() {
        Bishop bishop = new Bishop(BLACK);

        assertThatThrownBy(() -> bishop.checkCorrectDirection(Direction.NORTH))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> bishop.checkCorrectDirection(Direction.SOUTH))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> bishop.checkCorrectDirection(Direction.WEST))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> bishop.checkCorrectDirection(Direction.EAST))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Bishop의 가능한 방향 확인")
    @Test
    void checkBishopImpossibleMove() {
        Bishop bishop = new Bishop(BLACK);

        assertDoesNotThrow(() -> bishop.checkCorrectDirection(Direction.NORTH_WEST));
        assertDoesNotThrow(() -> bishop.checkCorrectDirection(Direction.NORTH_EAST));
        assertDoesNotThrow(() -> bishop.checkCorrectDirection(Direction.SOUTH_EAST));
        assertDoesNotThrow(() -> bishop.checkCorrectDirection(Direction.SOUTH_WEST));
    }
}
