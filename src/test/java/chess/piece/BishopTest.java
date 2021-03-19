package chess.piece;

import chess.domain.Point;
import chess.domain.piece.Bishop;
import chess.domain.piece.Direction;
import chess.domain.piece.Empty;
import chess.domain.piece.Pieces;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.ChessGame.BLACK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BishopTest {
    @DisplayName("Bishop 생성")
    @Test
    public void create() {
        Bishop bishop1 = new Bishop("B", "BLACK", Point.valueOf(0, 2));
        assertThat(Pieces.findPiece(0, 2)).isEqualTo(bishop1);
        Bishop bishop2 = new Bishop("B", "BLACK", Point.valueOf(0, 5));
        assertThat(Pieces.findPiece(0, 5)).isEqualTo(bishop2);
        Bishop bishop3 = new Bishop("b", "WHITE", Point.valueOf(7, 2));
        assertThat(Pieces.findPiece(7, 2)).isEqualTo(bishop3);
        Bishop bishop4 = new Bishop("b", "WHITE", Point.valueOf(7, 5));
        assertThat(Pieces.findPiece(7, 5)).isEqualTo(bishop4);
    }

    @DisplayName("Bishop의 가능한 방향 확인")
    @Test
    void checkBishopPossibleMove() {
        Bishop bishop = new Bishop("B", BLACK, Point.valueOf(4, 4));
        Empty empty = new Empty(".", null, Point.valueOf(5, 5));
        Empty empty2 = new Empty(".", null, Point.valueOf(3, 3));
        Empty empty3 = new Empty(".", null, Point.valueOf(3, 5));
        Empty empty4 = new Empty(".", null, Point.valueOf(5, 3));

        assertEquals(Direction.SOUTH_EAST, bishop.direction(empty));
        assertEquals(Direction.NORTH_WEST, bishop.direction(empty2));
        assertEquals(Direction.NORTH_EAST, bishop.direction(empty3));
        assertEquals(Direction.SOUTH_WEST, bishop.direction(empty4));
    }

    @DisplayName("Bishop의 불가능한 방향 확인")
    @Test
    void checkBishopImpossibleMove() {
        Bishop bishop = new Bishop("B", BLACK, Point.valueOf(0, 2));
        Empty empty = new Empty(".", null, Point.valueOf(5, 2));

        assertThatThrownBy(() -> bishop.direction(empty))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
