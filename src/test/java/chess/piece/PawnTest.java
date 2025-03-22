package chess.piece;

import chess.Color;
import chess.Fixtures;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @Test
    @DisplayName("화이트 폰은 A2에서 A3으로 이동할 수 있다.")
    void test1() {
        // given
        Pawn pawn = new Pawn(Color.WHITE, Fixtures.A2);

        // when
        pawn.move(Fixtures.A3);

        // then
        Assertions.assertThat(pawn.position())
                .isEqualTo(Fixtures.A3);
    }

    @Test
    @DisplayName("화이트 폰은 A3에서 A2로 이동할 수 없다.")
    void test2() {
        // given
        Pawn pawn = new Pawn(Color.WHITE, Fixtures.A3);

        // when
        // then
        Assertions.assertThatThrownBy(() -> pawn.move(Fixtures.A2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("폰은 A2에서 B2로 이동할 수 없다.")
    void test3() {
        // given
        Pawn pawn = new Pawn(Color.BLACK, Fixtures.A2);

        // when
        // then
        Assertions.assertThatThrownBy(() -> pawn.move(Fixtures.B2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("폰은 B2에서 A2로 이동할 수 없다.")
    void test4() {
        // given
        Pawn pawn = new Pawn(Color.BLACK, Fixtures.B2);

        // when
        // then
        Assertions.assertThatThrownBy(() -> pawn.move(Fixtures.A2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("블랙 폰은 A2에서 A1로 이동할 수 있다.")
    void test5() {
        // given
        Pawn pawn = new Pawn(Color.BLACK, Fixtures.A2);

        // when
        pawn.move(Fixtures.A1);

        // then
        Assertions.assertThat(pawn.position())
                .isEqualTo(Fixtures.A1);
    }

    @Test
    @DisplayName("화이트 폰은 A2에서 A3로 이동할 수 없다.")
    void test6() {
        // given
        Pawn pawn = new Pawn(Color.BLACK, Fixtures.A2);

        // when
        // then
        Assertions.assertThatThrownBy(() -> pawn.move(Fixtures.A3))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
