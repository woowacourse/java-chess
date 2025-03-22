package chess.piece;

import chess.Color;
import chess.Fixtures;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    @Test
    @DisplayName("나이트는 E4에서 C5으로 이동할 수 있다.")
    void test1() {
        // given
        Knight knight = new Knight(Color.EMPTY, Fixtures.E4);

        // when
        knight.move(Fixtures.C5);

        // then
        Assertions.assertThat(knight.position())
                .isEqualTo(Fixtures.C5);
    }

    @Test
    @DisplayName("나이트는 E4에서 C3으로 이동할 수 있다.")
    void test2() {
        // given
        Knight knight = new Knight(Color.EMPTY, Fixtures.E4);

        // when
        knight.move(Fixtures.C3);

        // then
        Assertions.assertThat(knight.position())
                .isEqualTo(Fixtures.C3);
    }

    @Test
    @DisplayName("나이트는 E4에서 D2으로 이동할 수 있다.")
    void test3() {
        // given
        Knight knight = new Knight(Color.EMPTY, Fixtures.E4);

        // when
        knight.move(Fixtures.D2);

        // then
        Assertions.assertThat(knight.position())
                .isEqualTo(Fixtures.D2);
    }

    @Test
    @DisplayName("나이트는 E4에서 F2으로 이동할 수 있다.")
    void test4() {
        // given
        Knight knight = new Knight(Color.EMPTY, Fixtures.E4);

        // when
        knight.move(Fixtures.F2);

        // then
        Assertions.assertThat(knight.position())
                .isEqualTo(Fixtures.F2);
    }

    @Test
    @DisplayName("나이트는 E4에서 C5으로 이동할 수 있다.")
    void test5() {
        // given
        Knight knight = new Knight(Color.EMPTY, Fixtures.E4);

        // when
        knight.move(Fixtures.G3);

        // then
        Assertions.assertThat(knight.position())
                .isEqualTo(Fixtures.G3);
    }

    @Test
    @DisplayName("나이트는 E4에서 G5으로 이동할 수 있다.")
    void test6() {
        // given
        Knight knight = new Knight(Color.EMPTY, Fixtures.E4);

        // when
        knight.move(Fixtures.G5);

        // then
        Assertions.assertThat(knight.position())
                .isEqualTo(Fixtures.G5);
    }

    @Test
    @DisplayName("나이트는 E4에서 F6으로 이동할 수 있다.")
    void test7() {
        // given
        Knight knight = new Knight(Color.EMPTY, Fixtures.E4);

        // when
        knight.move(Fixtures.F6);

        // then
        Assertions.assertThat(knight.position())
                .isEqualTo(Fixtures.F6);
    }

    @Test
    @DisplayName("나이트는 E4에서 D6으로 이동할 수 있다.")
    void test8() {
        // given
        Knight knight = new Knight(Color.EMPTY, Fixtures.E4);

        // when
        knight.move(Fixtures.D6);

        // then
        Assertions.assertThat(knight.position())
                .isEqualTo(Fixtures.D6);
    }

    @Test
    @DisplayName("나이트는 본인의 경로가 아니면 이동할 수 없다.")
    void test9() {
        // given
        Knight knight = new Knight(Color.EMPTY, Fixtures.E4);

        // when
        // then
        Assertions.assertThatThrownBy(() -> knight.move(Fixtures.C7))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
