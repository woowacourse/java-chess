package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;

class KingTest {

    private final King king = new King(Color.WHITE);

    @DisplayName("킹은 한 칸 앞으로 움직일 수 있다.")
    @Test
    void move_f1_f2() {
        //given
        Position f1 = Position.of(Column.F, Row.ONE);
        Position f2 = Position.of(Column.F, Row.TWO);
        //then
        assertThat(king.canMove(f1, f2)).isTrue();
    }

    @DisplayName("킹은 한 칸 앞으로 움직일 수 있다.")
    @Test
    void move_f1_g2() {
        //given
        Position f1 = Position.of(Column.F, Row.ONE);
        Position g2 = Position.of(Column.G, Row.TWO);
        //then
        assertThat(king.canMove(f1, g2)).isTrue();
    }

    @DisplayName("킹은 두 칸 앞으로 움직일 수 없다.")
    @Test
    void move_f1_f3() {
        //given
        Position f1 = Position.of(Column.F, Row.ONE);
        Position f3 = Position.of(Column.F, Row.THREE);
        //then
        assertThat(king.canMove(f1, f3)).isFalse();
    }
}