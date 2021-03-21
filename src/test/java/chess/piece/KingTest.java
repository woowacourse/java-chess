package chess.piece;

import chess.domain.Point;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Empty;
import chess.domain.piece.kind.King;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static chess.domain.piece.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        King king = new King(BLACK, Point.of(4, 4));
        Empty empty = new Empty(NOTHING, Point.of(5, 4));
        Empty empty2 = new Empty(NOTHING, Point.of(4, 5));
        Empty empty3 = new Empty(NOTHING, Point.of(3, 5));
        Empty empty4 = new Empty(NOTHING, Point.of(5, 3));

        assertEquals(Optional.of(Direction.SOUTH), king.direction(empty));
        assertEquals(Optional.of(Direction.EAST), king.direction(empty2));
        assertEquals(Optional.of(Direction.NORTH_EAST), king.direction(empty3));
        assertEquals(Optional.of(Direction.SOUTH_WEST), king.direction(empty4));
    }

    @DisplayName("King의 불가능한 거리 확인")
    @Test
    void checkKingImpossibleMove() {
        King king = new King(BLACK, Point.of(4, 4));
        Empty empty = new Empty(NOTHING, Point.of(2, 2));
        Empty empty2 = new Empty(NOTHING, Point.of(2, 6));
        Empty empty3 = new Empty(NOTHING, Point.of(4, 6));
        Empty empty4 = new Empty(NOTHING, Point.of(2, 4));

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
