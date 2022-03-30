package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;

public class BishopTest {

    private final Bishop bishop = new Bishop(Color.WHITE);

    @DisplayName("비숍은 대각선으로 움직일 수 있다.")
    @Test
    void move_d1_g4() {
        //given
        Position d1 = Position.of(Column.D, Row.ONE);
        Position g4 = Position.of(Column.G, Row.FOUR);
        //then
        assertThat(bishop.canMove(d1, g4)).isTrue();
    }

    @DisplayName("비숍은 앞으로 움직일 수 있다.")
    @Test
    void move_d1_d6() {
        //given
        Position d1 = Position.of(Column.D, Row.ONE);
        Position d6 = Position.of(Column.D, Row.SIX);
        //then
        assertThat(bishop.canMove(d1, d6)).isFalse();
    }

    @DisplayName("비숍은 뒤로 움직일 수 있다.")
    @Test
    void move_d6_d1() {
        //given
        Position d6 = Position.of(Column.D, Row.SEVEN);
        Position d1 = Position.of(Column.D, Row.ONE);
        //then
        assertThat(bishop.canMove(d6, d1)).isFalse();
    }

    @DisplayName("비숍은 우로 움직일 수 있다.")
    @Test
    void move_d1_e1() {
        //given
        Position d1 = Position.of(Column.D, Row.ONE);
        Position e1 = Position.of(Column.E, Row.ONE);
        //then
        assertThat(bishop.canMove(d1, e1)).isFalse();
    }

    @DisplayName("비숍은 좌로 움직일 수 있다.")
    @Test
    void move_e1_d1() {
        //given
        Position e1 = Position.of(Column.E, Row.ONE);
        Position d1 = Position.of(Column.D, Row.ONE);
        //then
        assertThat(bishop.canMove(e1, d1)).isFalse();
    }

    @DisplayName("비숍은 직선이 아닌 방향으로는 움직일 수 없다.")
    @Test
    void move_d1_f2() {
        //given
        Position d1 = Position.of(Column.D, Row.ONE);
        Position f2 = Position.of(Column.F, Row.TWO);
        //then
        assertThat(bishop.canMove(d1, f2)).isFalse();
    }
}
