package chess.piece;

import chess.Color;
import chess.Fixtures;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @Test
    @DisplayName("비숍은 대각선으로만 움직일 수 있다.")
    void test1() {
        // given
        Bishop bishop = new Bishop(Color.EMPTY, Fixtures.E4);

        // when
        bishop.move(Fixtures.B7);

        // then
        Assertions.assertThat(bishop.position())
                .isEqualTo(Fixtures.B7);
    }

    @Test
    @DisplayName("비숍은 대각선아니면 움직일 수 없다.")
    void test2() {
        // given
        Bishop bishop = new Bishop(Color.EMPTY, Fixtures.E4);

        // when
        // then
        Assertions.assertThatThrownBy(() -> bishop.move(Fixtures.B6))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
