package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Camp;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {
    private final King king = new King(Camp.WHITE);

    @DisplayName("킹은 한 칸 앞으로 움직일 수 있다.")
    @Test
    void move_f1_f2() {
        Position f1 = Position.of(Column.F, Row.ONE);
        Position f2 = Position.of(Column.F, Row.TWO);

        assertThat(king.canNotMove(f1, f2)).isFalse();
    }

    @DisplayName("킹은 한 칸 앞으로 움직일 수 있다.")
    @Test
    void move_f1_g2() {
        Position f1 = Position.of(Column.F, Row.ONE);
        Position g2 = Position.of(Column.G, Row.TWO);

        assertThat(king.canNotMove(f1, g2)).isFalse();
    }

    @DisplayName("킹은 두 칸 앞으로 움직일 수 없다.")
    @Test
    void move_f1_f3() {
        Position f1 = Position.of(Column.F, Row.ONE);
        Position f3 = Position.of(Column.F, Row.THREE);

        assertThat(king.canNotMove(f1, f3)).isTrue();
    }
}