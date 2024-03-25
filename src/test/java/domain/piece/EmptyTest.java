package domain.piece;

import static domain.PositionFixture.D_FOUR;
import static domain.PositionFixture.D_SEVEN;
import static org.assertj.core.api.Assertions.*;

import domain.board.position.Vector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmptyTest {

    @Test
    @DisplayName("비어있는 칸을 움직이려고 하면 예외가 발생한다")
    void moveNone() {
        final Piece empty = Empty.INSTANCE;
        final Piece other = new Rook(Color.BLACK);

        assertThatThrownBy(() -> empty.isReachable(new Vector(D_FOUR, D_SEVEN), other))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("비어 있는 칸입니다.");
    }
}
