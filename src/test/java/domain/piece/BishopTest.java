package domain.piece;

import static domain.PositionFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import domain.piece.info.Color;
import domain.piece.info.File;
import domain.piece.info.Position;
import domain.piece.info.Rank;
import domain.piece.info.Vector;
import org.assertj.core.api.Assertions;
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
