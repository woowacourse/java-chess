package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.Fixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookTest {

    @DisplayName("대각선으로 이동하려고 하면, 예외를 던진다.")
    @Test
    void throw_exception_move_diagonal() {
        Rook rook = new Rook(Fixtures.A1);

        assertThatThrownBy(() -> rook.move(1, 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("상하로 원하는 만큼 이동 가능하다.")
    @Test
    void move_vertical() {
        Rook rook = new Rook(Fixtures.A1);

        rook.move(0, 5);

        assertThat(rook.getPosition()).isEqualTo(Fixtures.A6);
    }

    @DisplayName("좌우로 원하는 만큼 이동 가능하다.")
    @Test
    void move_horizontal() {
        Rook rook = new Rook(Fixtures.A1);

        rook.move(5, 0);

        assertThat(rook.getPosition()).isEqualTo(Fixtures.F1);
    }

    @DisplayName("장기판 밖으로는 이동 불가능하다.")
    @ParameterizedTest
    @CsvSource(value = {
            "-1, 0",
            "100, 0",
            "0, -1",
            "0, 100"
    })
    void cant_move_out_of_board(int x, int y) {
        Rook rook = new Rook(Fixtures.A1);

        assertThatThrownBy(() -> rook.move(x, y))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
