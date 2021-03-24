package chess.piece;

import static chess.domain.piece.Color.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Point;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Empty;
import chess.domain.piece.kind.Queen;

public class QueenTest {
    @DisplayName("Queen 생성")
    @Test
    public void create() {
        Queen queen1 = new Queen(BLACK, Point.valueOf(0, 3));
        assertThat(PieceType.findPiece(0, 3)).isEqualTo(queen1);
        Queen queen2 = new Queen(WHITE, Point.valueOf(7, 3));
        assertThat(PieceType.findPiece(7, 3)).isEqualTo(queen2);
    }

    @DisplayName("Queen 이동 확인")
    @Test
    void checkQueenPossibleMove() {
        Queen queen = new Queen(BLACK, Point.valueOf(4, 4));
        Empty empty = new Empty(NOTHING, Point.valueOf(5, 4));
        Empty empty2 = new Empty(NOTHING, Point.valueOf(4, 5));
        Empty empty3 = new Empty(NOTHING, Point.valueOf(3, 3));
        Empty empty4 = new Empty(NOTHING, Point.valueOf(5, 5));

        assertEquals(Direction.SOUTH, queen.direction(empty));
        assertEquals(Direction.EAST, queen.direction(empty2));
        assertEquals(Direction.NORTH_WEST, queen.direction(empty3));
        assertEquals(Direction.SOUTH_EAST, queen.direction(empty4));
    }

    @DisplayName("Queen의 불가능한 위치 확인")
    @Test
    void checkQueenImpossibleMove() {
        Queen queen = new Queen(BLACK, Point.valueOf(4, 4));
        Empty empty = new Empty(NOTHING, Point.valueOf(2, 3));
        Empty empty2 = new Empty(NOTHING, Point.valueOf(6, 5));
        Empty empty3 = new Empty(NOTHING, Point.valueOf(2, 5));
        Empty empty4 = new Empty(NOTHING, Point.valueOf(6, 3));

        assertThatThrownBy(() -> queen.direction(empty))
            .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> queen.direction(empty2))
            .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> queen.direction(empty3))
            .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> queen.direction(empty4))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
