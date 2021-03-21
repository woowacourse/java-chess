package chess.piece;

import chess.domain.Point;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Empty;
import chess.domain.piece.kind.Queen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static chess.domain.piece.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        Queen queen = new Queen(BLACK, Point.of(4, 4));
        Empty empty = new Empty(NOTHING, Point.of(5, 4));
        Empty empty2 = new Empty(NOTHING, Point.of(4, 5));
        Empty empty3 = new Empty(NOTHING, Point.of(3, 3));
        Empty empty4 = new Empty(NOTHING, Point.of(5, 5));

        assertEquals(Optional.of(Direction.SOUTH), queen.direction(empty));
        assertEquals(Optional.of(Direction.EAST), queen.direction(empty2));
        assertEquals(Optional.of(Direction.NORTH_WEST), queen.direction(empty3));
        assertEquals(Optional.of(Direction.SOUTH_EAST), queen.direction(empty4));
    }

    @DisplayName("Queen의 불가능한 위치 확인")
    @Test
    void checkQueenImpossibleMove() {
        Queen queen = new Queen(BLACK, Point.of(4, 4));
        Empty empty = new Empty(NOTHING, Point.of(2, 3));
        Empty empty2 = new Empty(NOTHING, Point.of(6, 5));
        Empty empty3 = new Empty(NOTHING, Point.of(2, 5));
        Empty empty4 = new Empty(NOTHING, Point.of(6, 3));

        assertThatThrownBy(() -> queen.direction(empty))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> queen.direction(empty2))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> queen.direction(empty3))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> queen.direction(empty4))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
