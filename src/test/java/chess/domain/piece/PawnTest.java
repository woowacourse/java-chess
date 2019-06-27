package chess.domain.piece;

import chess.domain.board.Point;
import org.junit.jupiter.api.Test;

import static chess.domain.board.PlayerType.BLACK;
import static chess.domain.board.PlayerType.WHITE;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class PawnTest {
    @Test
    void isMovable_Black_yDistance1_whenFirst_True() {
        Pawn pawn = new Pawn(BLACK);
        assertThat(pawn.isMovable(Point.of(1, 1), Point.of(1, 2))).isTrue();
    }

    @Test
    void isMovable_Black_yDistance2_whenFirst_True() {
        Pawn pawn = new Pawn(BLACK);
        assertThat(pawn.isMovable(Point.of(1, 1), Point.of(1, 3))).isTrue();
    }

    @Test
    void isMovable_Black_LeftDiagonal1_whenFirst_True() {
        Pawn pawn = new Pawn(BLACK);
        assertThat(pawn.isMovable(Point.of(1, 1), Point.of(2, 2))).isTrue();
    }

    @Test
    void isMovable_Black_RightDiagonal1_whenFirst_True() {
        Pawn pawn = new Pawn(BLACK);
        assertThat(pawn.isMovable(Point.of(1, 1), Point.of(0, 2))).isTrue();
    }

    @Test
    void isMovable_Black_xDistance1_False() {
        Pawn pawn = new Pawn(BLACK);
        assertThat(pawn.isMovable(Point.of(1, 1), Point.of(2, 1))).isFalse();
    }

    @Test
    void isMovable_Black_yDistance2_False() {
        Pawn pawn = new Pawn(BLACK);
        assertThat(pawn.isMovable(Point.of(1, 3), Point.of(1, 5))).isFalse();
    }

    @Test
    void isMovable_Black_BackWard_False() {
        Pawn pawn = new Pawn(BLACK);
        assertThat(pawn.isMovable(Point.of(1, 3), Point.of(1, 2))).isFalse();
    }

    @Test
    void isMovable_White_yDistance1_whenFirst_True() {
        Pawn pawn = new Pawn(WHITE);
        assertThat(pawn.isMovable(Point.of(1, 6), Point.of(1, 5))).isTrue();
    }

    @Test
    void isMovable_White_yDistance2_whenFirst_True() {
        Pawn pawn = new Pawn(WHITE);
        assertThat(pawn.isMovable(Point.of(1, 6), Point.of(1, 4))).isTrue();
    }

    @Test
    void isMovable_White_LeftDiagonal1_whenFirst_True() {
        Pawn pawn = new Pawn(WHITE);
        assertThat(pawn.isMovable(Point.of(1, 6), Point.of(2, 5))).isTrue();
    }

    @Test
    void isMovable_White_RightDiagonal1_whenFirst_True() {
        Pawn pawn = new Pawn(WHITE);
        assertThat(pawn.isMovable(Point.of(1, 6), Point.of(0, 5))).isTrue();
    }

    @Test
    void isMovable_White_xDistance1_False() {
        Pawn pawn = new Pawn(WHITE);
        assertThat(pawn.isMovable(Point.of(1, 6), Point.of(2, 6))).isFalse();
    }

    @Test
    void isMovable_White_yDistance2_False() {
        Pawn pawn = new Pawn(WHITE);
        assertThat(pawn.isMovable(Point.of(1, 5), Point.of(1, 3))).isFalse();
    }

    @Test
    void isMovable_White_BackWard_False() {
        Pawn pawn = new Pawn(WHITE);
        assertThat(pawn.isMovable(Point.of(1, 6), Point.of(1, 7))).isFalse();
    }
}
