package chess.piece;

import chess.domain.Point;
import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.ChessGame.BLACK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KingTest {
    @DisplayName("King 생성")
    @Test
    public void create() {
        King king1 = new King("K", "BLACK", Point.valueOf(0, 4));
        assertThat(Pieces.findPiece(0, 4)).isEqualTo(king1);
        King king2 = new King("k", "WHITE", Point.valueOf(7, 4));
        assertThat(Pieces.findPiece(7, 4)).isEqualTo(king2);
    }

    @DisplayName("King의 가능한 거리 확인")
    @Test
    void checkKingPossibleMove() {
        King king = new King("K", BLACK, Point.valueOf(4, 4));
        Empty empty = new Empty(".", null, Point.valueOf(5, 4));
        Empty empty2 = new Empty(".", null, Point.valueOf(4, 5));
        Empty empty3 = new Empty(".", null, Point.valueOf(3, 5));
        Empty empty4 = new Empty(".", null, Point.valueOf(5, 3));

        assertEquals(Direction.SOUTH, king.direction(empty));
        assertEquals(Direction.EAST, king.direction(empty2));
        assertEquals(Direction.NORTH_EAST, king.direction(empty3));
        assertEquals(Direction.SOUTH_WEST, king.direction(empty4));
    }

    @DisplayName("King의 불가능한 거리 확인")
    @Test
    void checkKingImpossibleMove() {
        King king = new King("K", BLACK, Point.valueOf(4, 4));
        Empty empty = new Empty(".", null, Point.valueOf(2, 2));
        Empty empty2 = new Empty(".", null, Point.valueOf(2, 6));
        Empty empty3 = new Empty(".", null, Point.valueOf(4, 6));
        Empty empty4 = new Empty(".", null, Point.valueOf(2, 4));

        assertThatThrownBy(() -> king.direction(empty))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> king.direction(empty2))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> king.direction(empty3))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> king.direction(empty4))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
