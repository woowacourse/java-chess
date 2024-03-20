package chess.domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.piece.Color;
import chess.domain.piece.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @DisplayName("비숍을 대각선으로 이동한다.")
    @Test
    void canMove() {
        // given
        final Bishop bishop = new Bishop(Color.BLACK, new Position('d', 5));

        // when
        boolean canMove = bishop.canMoveTo(new Position('e', 4));

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("비숍은 대각선 이외로는 이동할 수 없다.")
    @Test
    void canNotMove() {
        // given
        final Bishop bishop = new Bishop(Color.BLACK, new Position('d', 5));

        // when
        boolean canMove = bishop.canMoveTo(new Position('d', 6)); // 대각선 위

        // then
        assertThat(canMove).isFalse();
    }
}
