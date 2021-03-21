package chess.piece;

import chess.domain.Point;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Bishop;
import chess.domain.piece.kind.Empty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static chess.domain.piece.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        Bishop bishop = new Bishop(BLACK, Point.of(4, 4));
        Empty empty = new Empty(NOTHING, Point.of(5, 5));
        Empty empty2 = new Empty(NOTHING, Point.of(3, 3));
        Empty empty3 = new Empty(NOTHING, Point.of(3, 5));
        Empty empty4 = new Empty(NOTHING, Point.of(5, 3));

        assertEquals(Optional.of(Direction.SOUTH_EAST), bishop.direction(empty));
        assertEquals(Optional.of(Direction.NORTH_WEST), bishop.direction(empty2));
        assertEquals(Optional.of(Direction.NORTH_EAST), bishop.direction(empty3));
        assertEquals(Optional.of(Direction.SOUTH_WEST), bishop.direction(empty4));
    }

    @DisplayName("Bishop의 불가능한 방향 확인")
    @Test
    void checkBishopImpossibleMove() {
        Bishop bishop = new Bishop(BLACK, Point.of(0, 2));
        Empty empty = new Empty(NOTHING, Point.of(5, 2));

        assertThatThrownBy(() -> bishop.direction(empty))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
