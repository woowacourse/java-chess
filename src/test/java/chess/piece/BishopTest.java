package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.Color;
import chess.Fixtures;
import chess.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @DisplayName("대각선으로 이동할 수 있다.")
    @Test
    void move_diagonal() {
        Position current = Fixtures.C1;
        Position dest = Fixtures.B2;
        Bishop bishop = new Bishop(Color.WHITE, current);

        bishop.move(-1, 1);

        assertThat(bishop.getPosition()).isEqualTo(dest);
    }

    @DisplayName("대각선이 아니라면 이동할 수 없다.")
    @Test
    void cant_move_not_diagonal() {
        Position current = Fixtures.C1;
        Bishop bishop = new Bishop(Color.WHITE, current);

        assertThatThrownBy(() -> bishop.move(1, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
