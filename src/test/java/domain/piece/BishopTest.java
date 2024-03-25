package domain.piece;

import static domain.PositionFixture.A_ONE;
import static domain.PositionFixture.A_SEVEN;
import static domain.PositionFixture.D_FOUR;
import static domain.PositionFixture.G_ONE;
import static domain.PositionFixture.G_SEVEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.board.position.Position;
import domain.board.position.Vector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {
    /*
            4.....1.  7
            .x...x..  6
            ..x.x...  5
            ...B....  4
            ..x.x...  3
            .x...x..  2
            3.....2.  1 (rank 1)

            abcdefgh
         */
    @Test
    @DisplayName("비숍은 대각선 방향으로 움직일 수 있다")
    void isDiagonal() {
        final Piece bishop = new Bishop(Color.BLACK);
        final Position source = D_FOUR;

        assertAll(
                () -> assertThat(bishop.isReachable(new Vector(source, G_SEVEN), Empty.INSTANCE)).isTrue(),
                () -> assertThat(bishop.isReachable(new Vector(source, G_ONE), Empty.INSTANCE)).isTrue(),
                () -> assertThat(bishop.isReachable(new Vector(source, A_ONE), Empty.INSTANCE)).isTrue(),
                () -> assertThat(bishop.isReachable(new Vector(source, A_SEVEN), Empty.INSTANCE)).isTrue()
        );
    }
}
