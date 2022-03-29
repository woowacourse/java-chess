package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Camp;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @DisplayName("킹은 한 칸 앞으로 움직일 수 있다.")
    @Test
    void move_f1_f2() {
        King king = new King(Camp.WHITE);
        Position f1 = Position.of(Column.F, Row.ONE);
        Position f2 = Position.of(Column.F, Row.TWO);

        assertThat(king.canMove(f1, f2)).isTrue();
    }

    @DisplayName("킹은 한 칸 앞으로 움직일 수 있다.")
    @Test
    void move_f1_g2() {
        King king = new King(Camp.WHITE);
        Position f1 = Position.of(Column.F, Row.ONE);
        Position g2 = Position.of(Column.G, Row.TWO);

        assertThat(king.canMove(f1, g2)).isTrue();
    }

    @DisplayName("킹은 두 칸 앞으로 움직일 수 없다.")
    @Test
    void move_f1_f3() {
        King king = new King(Camp.WHITE);
        Position f1 = Position.of(Column.F, Row.ONE);
        Position f3 = Position.of(Column.F, Row.THREE);

        assertThat(king.canMove(f1, f3)).isFalse();
    }
}
