package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.Square;
import chess.domain.piece.Color;
import chess.domain.piece.Rook;

public class RookTest {
    @Test
    @DisplayName("A1에 있는 룩을 A3로 이동 가능하다")
    void canMove_a1_a3() {
        Rook rook = new Rook(Color.BLACK);
        Boolean canMove = rook.canMove(new Square("a1"), new Square("a3"));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("A1에 있는 룩을 B3로 이동 불가능하다")
    void canMove_a1_b3() {
        Rook rook = new Rook(Color.BLACK);
        Boolean canMove = rook.canMove(new Square("a1"), new Square("b3"));

        assertThat(canMove).isFalse();
    }

    @Test
    @DisplayName("A1에 있는 룩을 B1로 이동 가능하다")
    void canMove_a1_b1() {
        Rook rook = new Rook(Color.BLACK);
        Boolean canMove = rook.canMove(new Square("a1"), new Square("b1"));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("A3에 있는 룩을 A1로 이동 가능하다")
    void canMove_a3_a1() {
        Rook rook = new Rook(Color.BLACK);
        Boolean canMove = rook.canMove(new Square("a3"), new Square("a1"));

        assertThat(canMove).isTrue();
    }
}
