package chess.piece;

import chess.domain.Point;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Empty;
import chess.domain.piece.kind.Knight;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class KnightTest {
    @DisplayName("Knight 생성")
    @Test
    public void create() {
        Knight knight7 = new Knight(BLACK, Point.of(0, 1));
        assertThat(PieceType.findPiece(0, 1)).isEqualTo(knight7);
        Knight knight2 = new Knight(BLACK, Point.of(0, 6));
        assertThat(PieceType.findPiece(0, 6)).isEqualTo(knight2);
        Knight knight3 = new Knight(WHITE, Point.of(7, 1));
        assertThat(PieceType.findPiece(7, 1)).isEqualTo(knight3);
        Knight knight4 = new Knight(WHITE, Point.of(7, 6));
        assertThat(PieceType.findPiece(7, 6)).isEqualTo(knight4);
    }

    @DisplayName("Knight의 가능한 거리 확인")
    @Test
    void checkKnightPossibleMove() {
        Point source = Point.of(4, 4);
        Knight knight = new Knight(BLACK, source);

        Empty empty1 = new Empty(NOTHING, Point.of(2, 5));
        Empty empty2 = new Empty(NOTHING, Point.of(2, 3));
        Empty empty3 = new Empty(NOTHING, Point.of(3, 6));
        Empty empty4 = new Empty(NOTHING, Point.of(3, 2));
        Empty empty5 = new Empty(NOTHING, Point.of(6, 5));
        Empty empty6 = new Empty(NOTHING, Point.of(6, 3));
        Empty empty7 = new Empty(NOTHING, Point.of(5, 6));
        Empty empty8 = new Empty(NOTHING, Point.of(5, 2));

        Direction direction1 = Direction.findDirection(source, Point.of(2, 5));
        Direction direction2 = Direction.findDirection(source, Point.of(2, 3));
        Direction direction3 = Direction.findDirection(source, Point.of(3, 6));
        Direction direction4 = Direction.findDirection(source, Point.of(3, 2));
        Direction direction5 = Direction.findDirection(source, Point.of(6, 5));
        Direction direction6 = Direction.findDirection(source, Point.of(6, 3));
        Direction direction7 = Direction.findDirection(source, Point.of(5, 6));
        Direction direction8 = Direction.findDirection(source, Point.of(5, 2));

        assertDoesNotThrow(() -> knight.validateMovable(direction1, empty1));
        assertDoesNotThrow(() -> knight.validateMovable(direction2, empty2));
        assertDoesNotThrow(() -> knight.validateMovable(direction3, empty3));
        assertDoesNotThrow(() -> knight.validateMovable(direction4, empty4));
        assertDoesNotThrow(() -> knight.validateMovable(direction5, empty5));
        assertDoesNotThrow(() -> knight.validateMovable(direction6, empty6));
        assertDoesNotThrow(() -> knight.validateMovable(direction7, empty7));
        assertDoesNotThrow(() -> knight.validateMovable(direction8, empty8));
    }

    @DisplayName("Knight의 불가능한 거리 확인")
    @Test
    void checkKnightImpossibleMove() {
        Point source = Point.of(4, 4);
        Knight knight = new Knight(BLACK, source);

        Empty empty1 = new Empty(NOTHING, Point.of(2, 2));
        Empty empty2 = new Empty(NOTHING, Point.of(2, 6));
        Empty empty3 = new Empty(NOTHING, Point.of(4, 6));
        Empty empty4 = new Empty(NOTHING, Point.of(2, 4));

        Direction direction1 = Direction.findDirection(source, Point.of(2, 2));
        Direction direction2 = Direction.findDirection(source, Point.of(2, 6));
        Direction direction3 = Direction.findDirection(source, Point.of(4, 6));
        Direction direction4 = Direction.findDirection(source, Point.of(2, 4));

        assertThatThrownBy(() -> knight.validateMovable(direction1, empty1))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> knight.validateMovable(direction2, empty2))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> knight.validateMovable(direction3, empty3))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> knight.validateMovable(direction4, empty4))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
