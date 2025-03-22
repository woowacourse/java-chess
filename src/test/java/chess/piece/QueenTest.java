package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.Fixtures;
import chess.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenTest {

    @DisplayName("상하로 원하는 만큼 이동 가능하다.")
    @Test
    void move_vertical() {
        Queen queen = new Queen(Fixtures.A1);

        queen.move(0, 5);

        assertThat(queen.getPosition()).isEqualTo(Fixtures.A6);
    }

    @DisplayName("좌우로 원하는 만큼 이동 가능하다.")
    @Test
    void move_horizontal() {
        Queen queen = new Queen(Fixtures.A1);

        queen.move(5, 0);

        assertThat(queen.getPosition()).isEqualTo(Fixtures.F1);
    }

    @DisplayName("대각선으로 이동할 수 있다.")
    @Test
    void move_diagonal() {
        Position current = Fixtures.C1;
        Position dest = Fixtures.B2;
        Queen queen = new Queen(current);

        queen.move(-1, 1);

        assertThat(queen.getPosition()).isEqualTo(dest);
    }

    @DisplayName("상하좌우 대각선외에는 이동 불가능하다.")
    @ParameterizedTest
    @CsvSource(value = {
            "5, 4",
            "2, 1",
    })
    void cant_move_not_diagonal_not_horizontal_or_vertical(int x, int y) {
        Position current = Fixtures.C1;
        Queen queen = new Queen(current);

        assertThatThrownBy(() -> queen.move(x, y))
                .isInstanceOf(IllegalArgumentException.class);
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
        Queen queen = new Queen(Fixtures.A1);

        assertThatThrownBy(() -> queen.move(x, y))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
