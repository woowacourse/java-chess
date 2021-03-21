package chess.piece;

import chess.domain.Point;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Empty;
import chess.domain.piece.kind.Queen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueenTest {
    @DisplayName("Queen 생성")
    @Test
    public void create() {
        Queen queen1 = new Queen(BLACK, Point.of(0, 3));
        assertThat(PieceType.findPiece(0, 3)).isEqualTo(queen1);
        Queen queen2 = new Queen(WHITE, Point.of(7, 3));
        assertThat(PieceType.findPiece(7, 3)).isEqualTo(queen2);
    }

    @DisplayName("Queen 이동 확인")
    @Test
    void checkQueenPossibleMove() {
        Point source = Point.of(4, 4);
        Queen queen = new Queen(BLACK, source);
        Empty empty1 = new Empty(NOTHING, Point.of(5, 4));
        Empty empty2 = new Empty(NOTHING, Point.of(4, 5));
        Empty empty3 = new Empty(NOTHING, Point.of(3, 3));
        Empty empty4 = new Empty(NOTHING, Point.of(5, 5));

        Direction direction1 = Direction.findDirection(source, Point.of(5, 4));
        Direction direction2 = Direction.findDirection(source, Point.of(4, 5));
        Direction direction3 = Direction.findDirection(source, Point.of(3, 3));
        Direction direction4 = Direction.findDirection(source, Point.of(5, 5));

        assertEquals(direction1, Direction.SOUTH);
        assertEquals(direction2, Direction.EAST);
        assertEquals(direction3, Direction.NORTH_WEST);
        assertEquals(direction4, Direction.SOUTH_EAST);

        assertDoesNotThrow(() -> queen.validateMovable(direction1, empty1));
        assertDoesNotThrow(() -> queen.validateMovable(direction2, empty2));
        assertDoesNotThrow(() -> queen.validateMovable(direction3, empty3));
        assertDoesNotThrow(() -> queen.validateMovable(direction4, empty4));
    }

    @DisplayName("Queen의 불가능한 위치 확인")
    @Test
    void checkQueenImpossibleMove() {
        Point source = Point.of(4, 4);
        Queen queen = new Queen(BLACK, source);

        Empty empty1 = new Empty(NOTHING, Point.of(2, 3));
        Empty empty2 = new Empty(NOTHING, Point.of(6, 5));
        Empty empty3 = new Empty(NOTHING, Point.of(2, 5));
        Empty empty4 = new Empty(NOTHING, Point.of(6, 3));

        Direction direction1 = Direction.findDirection(source, Point.of(2, 3));
        Direction direction2 = Direction.findDirection(source, Point.of(6, 5));
        Direction direction3 = Direction.findDirection(source, Point.of(2, 5));
        Direction direction4 = Direction.findDirection(source, Point.of(6, 3));

        assertThatThrownBy(() -> queen.validateMovable(direction1, empty1))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> queen.validateMovable(direction2, empty2))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> queen.validateMovable(direction3, empty3))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> queen.validateMovable(direction4, empty4))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
