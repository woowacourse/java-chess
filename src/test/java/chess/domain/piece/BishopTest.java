package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Camp;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BishopTest {

    @DisplayName("비숍은 대각선으로 움직일 수 있다.")
    @Test
    void move_d1_g4() {
        Bishop bishop = new Bishop(Camp.WHITE);

        Position d1 = new Position(Column.D, Row.ONE);
        Position g4 = new Position(Column.G, Row.FOUR);

        assertThat(bishop.canMove(d1, g4)).isTrue();
    }

    @DisplayName("비숍은 앞으로 움직일 수 있다.")
    @Test
    void move_d1_d6() {
        Bishop bishop = new Bishop(Camp.WHITE);

        Position d1 = new Position(Column.D, Row.ONE);
        Position d6 = new Position(Column.D, Row.SEVEN);

        assertThat(bishop.canMove(d1, d6)).isFalse();
    }

    @DisplayName("비숍은 뒤로 움직일 수 있다.")
    @Test
    void move_d6_d1() {
        Bishop bishop = new Bishop(Camp.WHITE);

        Position d6 = new Position(Column.D, Row.SEVEN);
        Position d1 = new Position(Column.D, Row.ONE);

        assertThat(bishop.canMove(d6, d1)).isFalse();
    }

    @DisplayName("비숍은 우로 움직일 수 있다.")
    @Test
    void move_d1_e1() {
        Bishop bishop = new Bishop(Camp.WHITE);

        Position d1 = new Position(Column.D, Row.ONE);
        Position e1 = new Position(Column.E, Row.ONE);

        assertThat(bishop.canMove(d1, e1)).isFalse();
    }

    @DisplayName("비숍은 좌로 움직일 수 있다.")
    @Test
    void move_e1_d1() {
        Bishop bishop = new Bishop(Camp.WHITE);

        Position e1 = new Position(Column.E, Row.ONE);
        Position d1 = new Position(Column.D, Row.ONE);

        assertThat(bishop.canMove(e1, d1)).isFalse();
    }

    @DisplayName("비숍은 직선이 아닌 방향으로는 움직일 수 없다.")
    @Test
    void move_d1_f2() {
        Bishop bishop = new Bishop(Camp.WHITE);

        Position d1 = new Position(Column.D, Row.ONE);
        Position f2 = new Position(Column.F, Row.TWO);

        assertThat(bishop.canMove(d1, f2)).isFalse();
    }
}
