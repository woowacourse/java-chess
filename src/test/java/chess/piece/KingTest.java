package chess.piece;

import chess.Color;
import chess.Fixtures;
import chess.Movement;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KingTest {

    @Test
    @DisplayName("킹은 E1에서 E2으로 이동할 수 있다.")
    void test1() {
        // given
        King king = new King(Color.BLACK, Fixtures.E1);

        // when
        king.move(Fixtures.E2);

        // then
        Assertions.assertThat(king.position())
                .isEqualTo(Fixtures.E2);
    }

    @Test
    @DisplayName("킹은 E2에서 E1로 이동할 수 있다.")
    void test2() {
        // given
        King king = new King(Color.BLACK, Fixtures.E2);

        // when
        king.move(Fixtures.E1);

        // then
        Assertions.assertThat(king.position())
                .isEqualTo(Fixtures.E1);
    }

    @Test
    @DisplayName("킹은 E2에서 D2로 이동할 수 있다.")
    void test3() {
        // given
        King king = new King(Color.BLACK, Fixtures.E2);

        // when
        king.move(Fixtures.D2);

        // then
        Assertions.assertThat(king.position())
                .isEqualTo(Fixtures.D2);
    }

    @Test
    @DisplayName("킹은 E2에서 F2로 이동할 수 있다.")
    void test4() {
        // given
        King king = new King(Color.BLACK, Fixtures.E2);

        // when
        king.move(Fixtures.F2);

        // then
        Assertions.assertThat(king.position())
                .isEqualTo(Fixtures.F2);
    }

    @Test
    @DisplayName("킹은 E2에서 D3로 이동할 수 있다.")
    void test5() {
        // given
        King king = new King(Color.BLACK, Fixtures.E2);

        // when
        king.move(Fixtures.D3);

        // then
        Assertions.assertThat(king.position())
                .isEqualTo(Fixtures.D3);
    }

    @Test
    @DisplayName("킹은 E2에서 D1로 이동할 수 있다.")
    void test6() {
        // given
        King king = new King(Color.BLACK, Fixtures.E2);

        // when
        king.move(Fixtures.D1);

        // then
        Assertions.assertThat(king.position())
                .isEqualTo(Fixtures.D1);
    }

    @Test
    @DisplayName("킹은 E2에서 F1로 이동할 수 있다.")
    void test7() {
        // given
        King king = new King(Color.BLACK, Fixtures.E2);

        // when
        king.move(Fixtures.F1);

        // then
        Assertions.assertThat(king.position())
                .isEqualTo(Fixtures.F1);
    }

    @Test
    @DisplayName("킹은 E2에서 F3로 이동할 수 있다.")
    void test8() {
        // given
        King king = new King(Color.BLACK, Fixtures.E2);

        // when
        king.move(Fixtures.F3);

        // then
        Assertions.assertThat(king.position())
                .isEqualTo(Fixtures.F3);
    }

    @Test
    @DisplayName("킹은 E2에서 E4로 이동할 수 없다.")
    void test9() {
        // given
        King king = new King(Color.BLACK, Fixtures.E2);

        // when
        // then
        Assertions.assertThatThrownBy(() -> king.move(Fixtures.E4))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("킹은 E2에서 G2로 이동할 수 없다.")
    void test10() {
        // given
        King king = new King(Color.BLACK, Fixtures.E2);

        // when
        // then
        Assertions.assertThatThrownBy(() -> king.move(Fixtures.G2))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
