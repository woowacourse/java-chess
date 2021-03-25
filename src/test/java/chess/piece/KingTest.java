package chess.piece;

import chess.domain.Point;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Empty;
import chess.domain.piece.kind.King;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KingTest {
    @DisplayName("King 생성")
    @Test
    public void create() {
        King king1 = new King(BLACK, Point.of(0, 4));
        assertThat(PieceType.findPiece(0, 4)).isEqualTo(king1);
        King king2 = new King(WHITE, Point.of(7, 4));
        assertThat(PieceType.findPiece(7, 4)).isEqualTo(king2);
    }

    @DisplayName("King의 가능한 거리 확인")
    @Test
    void checkKingPossibleMove() {
        Point source = Point.of(4, 4);
        King king = new King(BLACK, source);

        Empty empty1 = new Empty(NOTHING, Point.of(5, 4));
        Empty empty2 = new Empty(NOTHING, Point.of(4, 5));
        Empty empty3 = new Empty(NOTHING, Point.of(3, 5));
        Empty empty4 = new Empty(NOTHING, Point.of(5, 3));

        Direction direction1 = Direction.createDirection(source, Point.of(5, 4));
        Direction direction2 = Direction.createDirection(source, Point.of(4, 5));
        Direction direction3 = Direction.createDirection(source, Point.of(3, 5));
        Direction direction4 = Direction.createDirection(source, Point.of(5, 3));

        assertEquals(direction1, Direction.SOUTH);
        assertEquals(direction2, Direction.EAST);
        assertEquals(direction3, Direction.NORTH_EAST);
        assertEquals(direction4, Direction.SOUTH_WEST);

        assertDoesNotThrow(() -> king.validateMovableRoute(direction1, empty1));
        assertDoesNotThrow(() -> king.validateMovableRoute(direction2, empty2));
        assertDoesNotThrow(() -> king.validateMovableRoute(direction3, empty3));
        assertDoesNotThrow(() -> king.validateMovableRoute(direction4, empty4));
    }

    @DisplayName("King의 불가능한 거리 확인")
    @Test
    void checkKingImpossibleMove() {
        Point source = Point.of(4, 4);
        King king = new King(BLACK, source);

        Empty empty1 = new Empty(NOTHING, Point.of(2, 2));
        Empty empty2 = new Empty(NOTHING, Point.of(2, 6));
        Empty empty3 = new Empty(NOTHING, Point.of(4, 6));
        Empty empty4 = new Empty(NOTHING, Point.of(2, 4));

        Direction direction1 = Direction.createDirection(source, Point.of(2, 2));
        Direction direction2 = Direction.createDirection(source, Point.of(2, 6));
        Direction direction3 = Direction.createDirection(source, Point.of(4, 6));
        Direction direction4 = Direction.createDirection(source, Point.of(2, 4));

        assertThatThrownBy(() -> king.validateMovableRoute(direction1, empty1))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> king.validateMovableRoute(direction2, empty2))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> king.validateMovableRoute(direction3, empty3))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> king.validateMovableRoute(direction4, empty4))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
