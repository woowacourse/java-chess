package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.Fixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

    @DisplayName("상하좌우로 한 칸 이상 이동하면 예외를 던진다.")
    @ParameterizedTest
    @CsvSource(value = {
            "2, 0",
            "0, 2",
            "2, 1",
            "1, 2"
    })
    void cant_move_over_two_step(int x, int y) {
        King king = new King(Fixtures.D1);

        assertThatThrownBy(() -> king.move(x, y))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("상하로 한 칸 이동 가능하다.")
    @Test
    void move_vertical_one_step() {
        King king = new King(Fixtures.D1);

        king.move(0, 1);

        assertThat(king.getPosition()).isEqualTo(Fixtures.D2);
    }

    @DisplayName("좌우로 한 칸 이동 가능하다.")
    @Test
    void move_horizontal_one_step() {
        King king = new King(Fixtures.D1);

        king.move(1, 0);

        assertThat(king.getPosition()).isEqualTo(Fixtures.E1);
    }

    @DisplayName("대각선으로 한 칸 이동 가능하다.")
    @Test
    void move_diagonal_one_step() {
        King king = new King(Fixtures.D1);

        king.move(1, 1);

        assertThat(king.getPosition()).isEqualTo(Fixtures.E2);
    }

    @DisplayName("장기판 밖으로는 이동 불가능하다.")
    @ParameterizedTest
    @CsvSource(value = {
            "-1, 0",
            "0, -1",
    })
    void cant_move_out_of_board(int x, int y) {
        King king = new King(Fixtures.A1);

        assertThatThrownBy(() -> king.move(x, y))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
