package chess.piece;

import chess.Color;
import chess.Fixtures;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @Test
    @DisplayName("룩은 A1에서 B1~H1으로 이동할 수 있다.")
    void test1() {
        // given
        Rook rook = new Rook(Color.EMPTY, Fixtures.A1);

        // when
        rook.move(Fixtures.F1);

        // then
        Assertions.assertThat(rook.position())
                .isEqualTo(Fixtures.F1);
    }

    @Test
    @DisplayName("룩은 A1에서 A2~A8으로 이동할 수 있다.")
    void test2() {
        // given
        Rook rook = new Rook(Color.EMPTY, Fixtures.A1);

        // when
        rook.move(Fixtures.A7);

        // then
        Assertions.assertThat(rook.position())
                .isEqualTo(Fixtures.A7);
    }

    @Test
    @DisplayName("룩은 A1에서 B2로 이동할 수 없다.")
    void test3() {
        // given
        Rook rook = new Rook(Color.EMPTY, Fixtures.A1);

        // when
        // then
        Assertions.assertThatThrownBy(() -> rook.move(Fixtures.B2))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
