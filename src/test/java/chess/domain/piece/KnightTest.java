package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.Square;
import chess.domain.piece.Color;
import chess.domain.piece.Knight;

public class KnightTest {
    @Test
    @DisplayName("A1에 있는 나이트를 B3로 이동 가능하다")
    void canMove_a1_b3() {
        Knight knight = new Knight(Color.BLACK);
        Boolean canMove = knight.canMove(new Square("a1"), new Square("b3"));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("A1에 있는 나이트를 C2로 이동 가능하다")
    void canMove_a1_c2() {
        Knight knight = new Knight(Color.BLACK);
        Boolean canMove = knight.canMove(new Square("a1"), new Square("c2"));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("A1에 있는 나이트를 C3로 이동 불가능하다")
    void canMove_a1_c3() {
        Knight knight = new Knight(Color.BLACK);
        Boolean canMove = knight.canMove(new Square("a1"), new Square("c3"));

        assertThat(canMove).isFalse();
    }
}
