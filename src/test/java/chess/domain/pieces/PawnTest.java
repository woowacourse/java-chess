package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PawnTest {

    @Test
    void move_white() {
        Pawn pawn = new Pawn(ChessTeam.WHITE);
        assertThat(pawn.move(new Point(2, 2), new Point(2, 4))).isEqualTo(new Direction(0, 2));
    }

    @Test
    void move_black() {
        Pawn pawn = new Pawn(ChessTeam.BLACK);
        assertThat(pawn.move(new Point(2, 7), new Point(2, 5))).isEqualTo(new Direction(0, -2));
    }

    @Test
    void move_갈_수_없을_때_예외_테스트() {
        Pawn pawn = new Pawn(ChessTeam.BLACK);
        assertThat(pawn.move(new Point(2, 7), new Point(2, 5))).isEqualTo(new Direction(0, -2));
        assertThrows(IllegalArgumentException.class, () -> pawn.move(new Point(2, 5), new Point(2, 3)));
    }

    @Test
    void attack_white() {
        Pawn pawn = new Pawn(ChessTeam.WHITE);
        assertThat(pawn.attack(new Point(2, 2), new Point(3, 3))).isEqualTo(new Direction(1, 1));
    }

    @Test
    void attack_black() {
        Pawn pawn = new Pawn(ChessTeam.BLACK);
        assertThat(pawn.attack(new Point(2, 7), new Point(3, 6))).isEqualTo(new Direction(1, -1));
    }

    @Test
    void attack_예외처리_테스트() {
        Pawn pawn = new Pawn(ChessTeam.BLACK);
        assertThrows(IllegalArgumentException.class, () -> pawn.attack(new Point(2, 7), new Point(2, 6)));
    }
}