package chess.piece;

import chess.domain.Point;
import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.ChessGame.BLACK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RookTest {
    @DisplayName("Rook 생성")
    @Test
    public void create() {
        Rook rook1 = new Rook("R", "BLACK", Point.valueOf(0, 0));
        assertThat(Pieces.findPiece(0, 0)).isEqualTo(rook1);
        Rook rook2 = new Rook("R", "BLACK", Point.valueOf(0, 7));
        assertThat(Pieces.findPiece(0, 7)).isEqualTo(rook2);
        Rook rook3 = new Rook("r", "WHITE", Point.valueOf(7, 0));
        assertThat(Pieces.findPiece(7, 0)).isEqualTo(rook3);
        Rook rook4 = new Rook("r", "WHITE", Point.valueOf(7, 7));
        assertThat(Pieces.findPiece(7, 7)).isEqualTo(rook4);
    }
    
    @DisplayName("Rook의 가능한 방향 확인")
    @Test
    void checkRookPossibleMove() {
        Rook rook = new Rook("R", BLACK, Point.valueOf(4, 4));
        Empty empty = new Empty(".", null, Point.valueOf(5, 4));
        Empty empty2 = new Empty(".", null, Point.valueOf(4, 5));
        Empty empty3 = new Empty(".", null, Point.valueOf(3, 4));
        Empty empty4 = new Empty(".", null, Point.valueOf(4, 3));

        assertEquals(Direction.SOUTH, rook.direction(empty));
        assertEquals(Direction.EAST, rook.direction(empty2));
        assertEquals(Direction.NORTH, rook.direction(empty3));
        assertEquals(Direction.WEST, rook.direction(empty4));
    }

    @DisplayName("Rook의 불가능한 방향 확인")
    @Test
    void checkRookImpossibleMove() {
        Rook rook = new Rook("R", BLACK, Point.valueOf(4, 4));
        Empty empty = new Empty(".", null, Point.valueOf(3, 3));
        Empty empty2 = new Empty(".", null, Point.valueOf(5, 5));
        Empty empty3 = new Empty(".", null, Point.valueOf(5, 3));
        Empty empty4 = new Empty(".", null, Point.valueOf(3, 5));

        assertThatThrownBy(() -> rook.direction(empty))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> rook.direction(empty2))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> rook.direction(empty3))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> rook.direction(empty4))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
