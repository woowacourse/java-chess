package chess.piece;

import chess.domain.Point;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Bishop;
import chess.domain.piece.kind.Empty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BishopTest {
    @DisplayName("Bishop 생성")
    @Test
    public void create() {
        Bishop bishop1 = new Bishop(BLACK, Point.of(0, 2));
        assertThat(PieceType.findPiece(0, 2)).isEqualTo(bishop1);
        Bishop bishop2 = new Bishop(BLACK, Point.of(0, 5));
        assertThat(PieceType.findPiece(0, 5)).isEqualTo(bishop2);
        Bishop bishop3 = new Bishop(WHITE, Point.of(7, 2));
        assertThat(PieceType.findPiece(7, 2)).isEqualTo(bishop3);
        Bishop bishop4 = new Bishop(WHITE, Point.of(7, 5));
        assertThat(PieceType.findPiece(7, 5)).isEqualTo(bishop4);
    }

    @DisplayName("Bishop의 가능한 방향 확인")
    @Test
    void checkBishopPossibleMove() {
        Point source = Point.of(4, 4);
        Bishop bishop = new Bishop(BLACK, source);
        Empty empty1 = new Empty(NOTHING, Point.of(5, 5));
        Empty empty2 = new Empty(NOTHING, Point.of(3, 3));
        Empty empty3 = new Empty(NOTHING, Point.of(3, 5));
        Empty empty4 = new Empty(NOTHING, Point.of(5, 3));

        Direction direction1 = Direction.findDirection(source, Point.of(5, 5));
        Direction direction2 = Direction.findDirection(source, Point.of(3, 3));
        Direction direction3 = Direction.findDirection(source, Point.of(3, 5));
        Direction direction4 = Direction.findDirection(source, Point.of(5, 3));

        assertEquals(direction1, Direction.SOUTH_EAST);
        assertEquals(direction2, Direction.NORTH_WEST);
        assertEquals(direction3, Direction.NORTH_EAST);
        assertEquals(direction4, Direction.SOUTH_WEST);

        assertDoesNotThrow(() -> bishop.validateMovable(direction1, empty1));
        assertDoesNotThrow(() -> bishop.validateMovable(direction2, empty2));
        assertDoesNotThrow(() -> bishop.validateMovable(direction3, empty3));
        assertDoesNotThrow(() -> bishop.validateMovable(direction4, empty4));
    }

    @DisplayName("Bishop의 불가능한 방향 확인")
    @Test
    void checkBishopImpossibleMove() {
        Bishop bishop = new Bishop(BLACK, Point.of(0, 2));
        Empty empty = new Empty(NOTHING, Point.of(5, 2));

        Direction direction1 = Direction.findDirection(Point.of(0, 2), Point.of(5, 2));

        assertThatThrownBy(() -> bishop.validateMovable(direction1, empty))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
