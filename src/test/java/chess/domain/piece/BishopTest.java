package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Camp;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;

public class BishopTest {

    private final Bishop bishop = new Bishop(Camp.WHITE);

    @DisplayName("비숍은 대각선으로 움직일 수 있다.")
    @Test
    void move_d1_g4() {
        //given
        Position d1 = new Position(Column.D, Row.ONE);
        Position g4 = new Position(Column.G, Row.FOUR);
        //when
        //then
        assertThat(bishop.canMove(d1, g4)).isTrue();
    }

    @DisplayName("비숍은 앞으로 움직일 수 있다.")
    @Test
    void move_d1_d6() {
        //given
        Position d1 = new Position(Column.D, Row.ONE);
        Position d6 = new Position(Column.D, Row.SIX);
        //when
        //then
        assertThat(bishop.canMove(d1, d6)).isFalse();
    }

    @DisplayName("비숍은 뒤로 움직일 수 있다.")
    @Test
    void move_d6_d1() {
        //given
        Position d6 = new Position(Column.D, Row.SEVEN);
        Position d1 = new Position(Column.D, Row.ONE);
        //when
        //then
        assertThat(bishop.canMove(d6, d1)).isFalse();
    }

    @DisplayName("비숍은 우로 움직일 수 있다.")
    @Test
    void move_d1_e1() {
        //given
        Position d1 = new Position(Column.D, Row.ONE);
        Position e1 = new Position(Column.E, Row.ONE);
        //when
        //then
        assertThat(bishop.canMove(d1, e1)).isFalse();
    }

    @DisplayName("비숍은 좌로 움직일 수 있다.")
    @Test
    void move_e1_d1() {
        //given
        Position e1 = new Position(Column.E, Row.ONE);
        Position d1 = new Position(Column.D, Row.ONE);
        //when
        //then
        assertThat(bishop.canMove(e1, d1)).isFalse();
    }

    @DisplayName("비숍은 직선이 아닌 방향으로는 움직일 수 없다.")
    @Test
    void move_d1_f2() {
        //given
        Position d1 = new Position(Column.D, Row.ONE);
        Position f2 = new Position(Column.F, Row.TWO);
        //when
        //then
        assertThat(bishop.canMove(d1, f2)).isFalse();
    }
}
