package chess.piece;

import chess.domain.Point;
import chess.domain.piece.*;
import chess.domain.piece.kind.Empty;
import chess.domain.piece.kind.Knight;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static chess.domain.piece.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnightTest {
    @DisplayName("Knight 생성")
    @Test
    public void create() {
        Knight knight7 = new Knight(BLACK, Point.valueOf(0, 1));
        assertThat(PieceType.findPiece(0, 1)).isEqualTo(knight7);
        Knight knight2 = new Knight(BLACK, Point.valueOf(0, 6));
        assertThat(PieceType.findPiece(0, 6)).isEqualTo(knight2);
        Knight knight3 = new Knight(WHITE, Point.valueOf(7, 1));
        assertThat(PieceType.findPiece(7, 1)).isEqualTo(knight3);
        Knight knight4 = new Knight(WHITE, Point.valueOf(7, 6));
        assertThat(PieceType.findPiece(7, 6)).isEqualTo(knight4);
    }

    @DisplayName("Knight의 가능한 거리 확인")
    @Test
    void checkKnightPossibleMove() {
        Knight knight = new Knight(BLACK, Point.valueOf(4, 4));
        Empty empty = new Empty(NOTHING, Point.valueOf(2 , 5));
        Empty empty2 = new Empty(NOTHING, Point.valueOf(2, 3));
        Empty empty3 = new Empty(NOTHING, Point.valueOf(3, 6));
        Empty empty4 = new Empty(NOTHING, Point.valueOf(3, 2));
        Empty empty5 = new Empty(NOTHING, Point.valueOf(6, 5));
        Empty empty6 = new Empty(NOTHING, Point.valueOf(6, 3));
        Empty empty7 = new Empty(NOTHING, Point.valueOf(5, 6));
        Empty empty8 = new Empty(NOTHING, Point.valueOf(5, 2));

        assertEquals(Optional.empty(), knight.direction(empty));
        assertEquals(Optional.empty(), knight.direction(empty2));
        assertEquals(Optional.empty(), knight.direction(empty3));
        assertEquals(Optional.empty(), knight.direction(empty4));
        assertEquals(Optional.empty(), knight.direction(empty5));
        assertEquals(Optional.empty(), knight.direction(empty6));
        assertEquals(Optional.empty(), knight.direction(empty7));
        assertEquals(Optional.empty(), knight.direction(empty8));
    }

    @DisplayName("Knight의 불가능한 거리 확인")
    @Test
    void checkKnightImpossibleMove() {
        Knight knight = new Knight(BLACK, Point.valueOf(4, 4));
        Empty empty = new Empty(NOTHING, Point.valueOf(2, 2));
        Empty empty2 = new Empty(NOTHING, Point.valueOf(2, 6));
        Empty empty3 = new Empty(NOTHING, Point.valueOf(4, 6));
        Empty empty4 = new Empty(NOTHING, Point.valueOf(2, 4));

        assertThatThrownBy(() -> knight.direction(empty))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> knight.direction(empty2))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> knight.direction(empty3))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> knight.direction(empty4))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
