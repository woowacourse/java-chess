package chess.piece;

import chess.domain.Point;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Empty;
import chess.domain.piece.kind.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RookTest {
    @DisplayName("Rook 생성")
    @Test
    public void create() {
        Rook rook1 = new Rook(BLACK, Point.of(0, 0));
        assertThat(PieceType.findPiece(0, 0)).isEqualTo(rook1);
        Rook rook2 = new Rook(BLACK, Point.of(0, 7));
        assertThat(PieceType.findPiece(0, 7)).isEqualTo(rook2);
        Rook rook3 = new Rook(WHITE, Point.of(7, 0));
        assertThat(PieceType.findPiece(7, 0)).isEqualTo(rook3);
        Rook rook4 = new Rook(WHITE, Point.of(7, 7));
        assertThat(PieceType.findPiece(7, 7)).isEqualTo(rook4);
    }

    @DisplayName("Rook의 가능한 방향 확인")
    @Test
    void checkRookPossibleMove() {
        Point source = Point.of(4, 4);
        Rook rook = new Rook(BLACK, source);

        Empty empty1 = new Empty(NOTHING, Point.of(5, 4));
        Empty empty2 = new Empty(NOTHING, Point.of(4, 5));
        Empty empty3 = new Empty(NOTHING, Point.of(3, 4));
        Empty empty4 = new Empty(NOTHING, Point.of(4, 3));

        Direction direction1 = Direction.findDirection(source, Point.of(5, 4));
        Direction direction2 = Direction.findDirection(source, Point.of(4, 5));
        Direction direction3 = Direction.findDirection(source, Point.of(3, 4));
        Direction direction4 = Direction.findDirection(source, Point.of(4, 3));

        assertEquals(direction1, Direction.SOUTH);
        assertEquals(direction2, Direction.EAST);
        assertEquals(direction3, Direction.NORTH);
        assertEquals(direction4, Direction.WEST);

        assertDoesNotThrow(() -> rook.validateMovable(direction1, empty1));
        assertDoesNotThrow(() -> rook.validateMovable(direction2, empty2));
        assertDoesNotThrow(() -> rook.validateMovable(direction3, empty3));
        assertDoesNotThrow(() -> rook.validateMovable(direction4, empty4));
    }

    @DisplayName("Rook의 불가능한 방향 확인")
    @Test
    void checkRookImpossibleMove() {
        Point source = Point.of(4, 4);
        Rook rook = new Rook(BLACK, source);

        Empty empty1 = new Empty(NOTHING, Point.of(3, 3));
        Empty empty2 = new Empty(NOTHING, Point.of(5, 5));
        Empty empty3 = new Empty(NOTHING, Point.of(5, 3));
        Empty empty4 = new Empty(NOTHING, Point.of(3, 5));

        Direction direction1 = Direction.findDirection(source, Point.of(3, 3));
        Direction direction2 = Direction.findDirection(source, Point.of(5, 5));
        Direction direction3 = Direction.findDirection(source, Point.of(5, 3));
        Direction direction4 = Direction.findDirection(source, Point.of(3, 5));

        assertThatThrownBy(() -> rook.validateMovable(direction1, empty1))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> rook.validateMovable(direction2, empty2))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> rook.validateMovable(direction3, empty3))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> rook.validateMovable(direction4, empty4))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
