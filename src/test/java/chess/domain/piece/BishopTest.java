package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.Square;
import chess.domain.piece.Bishop;
import chess.domain.piece.Color;

public class BishopTest {
    @Test
    @DisplayName("A1에 있는 비숍을 C3로 이동 가능하다")
    void canMove_a1_c3() {
        Bishop bishop = new Bishop(Color.BLACK);
        Boolean canMove = bishop.canMove(new Square("a1"), new Square("c3"));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("D1에 있는 비숍을 A4로 이동 가능하다")
    void canMove_d1_a4() {
        Bishop bishop = new Bishop(Color.BLACK);
        Boolean canMove = bishop.canMove(new Square("d1"), new Square("a4"));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("A1에 있는 비숍을 A5로 이동 불가능하다")
    void canMove_a1_a5() {
        Bishop bishop = new Bishop(Color.BLACK);
        Boolean canMove = bishop.canMove(new Square("a1"), new Square("a5"));

        assertThat(canMove).isFalse();
    }
}
