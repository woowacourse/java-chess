package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Camp;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightTest {

    @DisplayName("나이트는 앞으로 2칸 왼쪽으로 1칸 움직일 수 있다.")
    @Test
    void move_c4_b6() {
        Knight knight = new Knight(Camp.WHITE);

        Position c4 = Position.of(Column.C, Row.FOUR);
        Position b6 = Position.of(Column.B, Row.SIX);

        assertThat(knight.canMove(c4, b6)).isTrue();
    }

    @DisplayName("나이트는 앞으로 2칸 오른쪽으로 1칸 움직일 수 있다.")
    @Test
    void move_c4_d6() {
        Knight knight = new Knight(Camp.WHITE);

        Position c4 = Position.of(Column.C, Row.FOUR);
        Position b6 = Position.of(Column.B, Row.SIX);

        assertThat(knight.canMove(c4, b6)).isTrue();
    }


    @DisplayName("나이트는 뒤로 2칸 왼쪽으로 1칸 움직일 수 있다.")
    @Test
    void move_c4_b2() {
        Knight knight = new Knight(Camp.WHITE);

        Position c4 = Position.of(Column.C, Row.FOUR);
        Position b2 = Position.of(Column.B, Row.TWO);

        assertThat(knight.canMove(c4, b2)).isTrue();
    }


    @DisplayName("나이트는 뒤로 2칸 오른쪽으로 1칸 움직일 수 있다.")
    @Test
    void move_c4_d2() {
        Knight knight = new Knight(Camp.WHITE);

        Position c4 = Position.of(Column.C, Row.FOUR);
        Position d2 = Position.of(Column.D, Row.TWO);

        assertThat(knight.canMove(c4, d2)).isTrue();
    }
    @DisplayName("나이트는 왼쪽으로 2칸 위로 1칸 움직일 수 있다.")
    @Test
    void move_c4_a5() {
        Knight knight = new Knight(Camp.WHITE);

        Position c4 = Position.of(Column.C, Row.FOUR);
        Position a5 = Position.of(Column.A, Row.FIVE);

        assertThat(knight.canMove(c4, a5)).isTrue();
    }

    @DisplayName("나이트는 왼쪽으로 2칸 아래로 1칸 움직일 수 있다.")
    @Test
    void move_c4_a3() {
        Knight knight = new Knight(Camp.WHITE);

        Position c4 = Position.of(Column.C, Row.FOUR);
        Position a3 = Position.of(Column.A, Row.THREE);

        assertThat(knight.canMove(c4, a3)).isTrue();
    }


    @DisplayName("나이트는 오른쪽으로 2칸 위로 1칸 움직일 수 있다.")
    @Test
    void move_c4_e5() {
        Knight knight = new Knight(Camp.WHITE);

        Position c4 = Position.of(Column.C, Row.FOUR);
        Position e5 = Position.of(Column.E, Row.FIVE);

        assertThat(knight.canMove(c4, e5)).isTrue();
    }


    @DisplayName("나이트는 오른쪽으로 2칸 아래로 1칸 움직일 수 있다.")
    @Test
    void move_c4_e3() {
        Knight knight = new Knight(Camp.WHITE);

        Position c4 = Position.of(Column.C, Row.FOUR);
        Position e3 = Position.of(Column.E, Row.THREE);

        assertThat(knight.canMove(c4, e3)).isTrue();
    }

    @DisplayName("나이트는 왼쪽으로 2칸만 움직일 수 없다.")
    @Test
    void move_c4_a4() {
        Knight knight = new Knight(Camp.WHITE);

        Position c4 = Position.of(Column.C, Row.FOUR);
        Position a4 = Position.of(Column.A, Row.FOUR);

        assertThat(knight.canMove(c4, a4)).isFalse();
    }

    @DisplayName("나이트는 오른쪽으로 2칸만 움직일 수 없다.")
    @Test
    void move_c4_e4() {
        Knight knight = new Knight(Camp.WHITE);

        Position c4 = Position.of(Column.C, Row.FOUR);
        Position e4 = Position.of(Column.E, Row.FOUR);

        assertThat(knight.canMove(c4, e4)).isFalse();
    }

    @DisplayName("나이트는 위로 3칸 왼쪽으로 1칸 움직일 수 없다.")
    @Test
    void move_c4_b7() {
        Knight knight = new Knight(Camp.WHITE);

        Position c4 = Position.of(Column.C, Row.FOUR);
        Position b7 = Position.of(Column.B, Row.SEVEN);

        assertThat(knight.canMove(c4, b7)).isFalse();
    }
}
