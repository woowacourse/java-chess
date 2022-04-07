package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Camp;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {
    @DisplayName("퀸은 앞으로 움직일 수 있다.")
    @Test
    void move_d1_d6() {
        Queen queen = new Queen(Camp.WHITE);

        Position d1 = Position.of(Column.D, Row.ONE);
        Position d6 = Position.of(Column.D, Row.SIX);

        assertThat(queen.canMove(d1, d6)).isTrue();
    }

    @DisplayName("퀸은 뒤로 움직일 수 있다.")
    @Test
    void move_d6_d1() {
        Queen queen = new Queen(Camp.WHITE);

        Position d6 = Position.of(Column.D, Row.SIX);
        Position d1 = Position.of(Column.D, Row.ONE);

        assertThat(queen.canMove(d6, d1)).isTrue();
    }

    @DisplayName("퀸은 우로 움직일 수 있다.")
    @Test
    void move_d1_e1() {
        Queen queen = new Queen(Camp.WHITE);

        Position d1 = Position.of(Column.D, Row.ONE);
        Position e1 = Position.of(Column.E, Row.ONE);

        assertThat(queen.canMove(d1, e1)).isTrue();
    }

    @DisplayName("퀸은 좌로 움직일 수 있다.")
    @Test
    void move_e1_d1() {
        Queen queen = new Queen(Camp.WHITE);

        Position e1 = Position.of(Column.E, Row.ONE);
        Position d1 = Position.of(Column.D, Row.ONE);

        assertThat(queen.canMove(e1, d1)).isTrue();
    }

    @DisplayName("퀸은 대각선으로 움직일 수 있다.")
    @Test
    void move_d1_g4() {
        Queen queen = new Queen(Camp.WHITE);

        Position d1 = Position.of(Column.D, Row.ONE);
        Position g4 = Position.of(Column.G, Row.FOUR);

        assertThat(queen.canMove(d1, g4)).isTrue();
    }

    @DisplayName("퀸은 직선이 아닌 방향으로는 움직일 수 없다.")
    @Test
    void move_d1_f2() {
        Queen queen = new Queen(Camp.WHITE);

        Position d1 = Position.of(Column.D, Row.ONE);
        Position f2 = Position.of(Column.F, Row.TWO);

        assertThat(queen.canMove(d1, f2)).isFalse();
    }
}
