package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Camp;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;

public class KnightTest {

    private final Knight knight = new Knight(Camp.WHITE);

    @DisplayName("나이트는 앞으로 2칸 왼쪽으로 1칸 움직일 수 있다.")
    @Test
    void move_c4_b6() {
        //given
        Position c4 = new Position(Column.C, Row.FOUR);
        Position b6 = new Position(Column.B, Row.SIX);
        //when
        //then
        assertThat(knight.canMove(c4, b6)).isTrue();
    }

    @DisplayName("나이트는 앞으로 2칸 오른쪽으로 1칸 움직일 수 있다.")
    @Test
    void move_c4_d6() {
        //given
        Position c4 = new Position(Column.C, Row.FOUR);
        Position b6 = new Position(Column.B, Row.SIX);
        //when
        //then
        assertThat(knight.canMove(c4, b6)).isTrue();
    }


    @DisplayName("나이트는 뒤로 2칸 왼쪽으로 1칸 움직일 수 있다.")
    @Test
    void move_c4_b2() {
        //given
        Position c4 = new Position(Column.C, Row.FOUR);
        Position b2 = new Position(Column.B, Row.TWO);
        //when
        //then
        assertThat(knight.canMove(c4, b2)).isTrue();
    }


    @DisplayName("나이트는 뒤로 2칸 오른쪽으로 1칸 움직일 수 있다.")
    @Test
    void move_c4_d2() {
        //given
        Position c4 = new Position(Column.C, Row.FOUR);
        Position d2 = new Position(Column.D, Row.TWO);
        //when
        //then
        assertThat(knight.canMove(c4, d2)).isTrue();
    }
    @DisplayName("나이트는 왼쪽으로 2칸 위로 1칸 움직일 수 있다.")
    @Test
    void move_c4_a5() {
        //given
        Position c4 = new Position(Column.C, Row.FOUR);
        Position a5 = new Position(Column.A, Row.FIVE);
        //when
        //then
        assertThat(knight.canMove(c4, a5)).isTrue();
    }

    @DisplayName("나이트는 왼쪽으로 2칸 아래로 1칸 움직일 수 있다.")
    @Test
    void move_c4_a3() {
        //given
        Position c4 = new Position(Column.C, Row.FOUR);
        Position a3 = new Position(Column.A, Row.THREE);
        //when
        //then
        assertThat(knight.canMove(c4, a3)).isTrue();
    }


    @DisplayName("나이트는 오른쪽으로 2칸 위로 1칸 움직일 수 있다.")
    @Test
    void move_c4_e5() {
        //given
        Position c4 = new Position(Column.C, Row.FOUR);
        Position e5 = new Position(Column.E, Row.FIVE);
        //when
        //then
        assertThat(knight.canMove(c4, e5)).isTrue();
    }


    @DisplayName("나이트는 오른쪽으로 2칸 아래로 1칸 움직일 수 있다.")
    @Test
    void move_c4_e3() {
        //given
        Position c4 = new Position(Column.C, Row.FOUR);
        Position e3 = new Position(Column.E, Row.THREE);
        //when
        //then
        assertThat(knight.canMove(c4, e3)).isTrue();
    }

    @DisplayName("나이트는 왼쪽으로 2칸만 움직일 수 없다.")
    @Test
    void move_c4_a4() {
        //given
        Position c4 = new Position(Column.C, Row.FOUR);
        Position a4 = new Position(Column.A, Row.FOUR);
        //when
        //then
        assertThat(knight.canMove(c4, a4)).isFalse();
    }

    @DisplayName("나이트는 오른쪽으로 2칸만 움직일 수 없다.")
    @Test
    void move_c4_e4() {
        //given
        Position c4 = new Position(Column.C, Row.FOUR);
        Position e4 = new Position(Column.E, Row.FOUR);
        //when
        //then
        assertThat(knight.canMove(c4, e4)).isFalse();
    }

    @DisplayName("나이트는 위로 3칸 왼쪽으로 1칸 움직일 수 없다.")
    @Test
    void move_c4_b7() {
        //given
        Position c4 = new Position(Column.C, Row.FOUR);
        Position b7 = new Position(Column.B, Row.SEVEN);
        //when
        //then
        assertThat(knight.canMove(c4, b7)).isFalse();
    }
}
