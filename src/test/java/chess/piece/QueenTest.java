package chess.piece;

import chess.Color;
import chess.Fixtures;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @Test
    @DisplayName("퀸은 대각, 직선 이동 가능")
    void test1() {
        // g
        Queen queen = new Queen(Color.EMPTY, Fixtures.D5);

        // w
        queen.move(Fixtures.A8);

        // t
        Assertions.assertThat(queen.position)
                .isEqualTo(Fixtures.A8);
    }

    @Test
    @DisplayName("퀸은 대각, 직선 이동 가능")
    void test2() {
        // g
        Queen queen = new Queen(Color.EMPTY, Fixtures.D5);

        // w
        queen.move(Fixtures.D8);

        // t
        Assertions.assertThat(queen.position)
                .isEqualTo(Fixtures.D8);
    }

    @Test
    @DisplayName("퀸은 대각, 직선 이동 가능")
    void test4() {
        // g
        Queen queen = new Queen(Color.EMPTY, Fixtures.D5);

        // w
        queen.move(Fixtures.C4);

        // t
        Assertions.assertThat(queen.position)
                .isEqualTo(Fixtures.C4);
    }
}
