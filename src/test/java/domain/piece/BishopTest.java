package domain.piece;

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
            T.....T.  7
            .x...x..  6
            ..x.x...  5
            ...B....  4
            ..x.x...  3
            .x...x..  2
            T.....T.  1 (rank 1)

            abcdefgh
         */
    @Test
    @DisplayName("비숍은 대각선 방향으로 움직일 수 있다")
    void isDiagonal() {
        final Piece bishop = new Bishop(Color.BLACK);
        final Position source = new Position(File.D, Rank.FOUR);
        final Position targetLeftUp = new Position(File.A, Rank.SEVEN);
        final Position targetRightUp = new Position(File.G, Rank.SEVEN);
        final Position targetLeftDown = new Position(File.A, Rank.ONE);
        final Position targetRightDown = new Position(File.G, Rank.ONE);

        assertAll(
                () -> Assertions.assertThat(bishop.isReachable(source, targetLeftUp, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(bishop.isReachable(source, targetRightUp, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(bishop.isReachable(source, targetLeftDown, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(bishop.isReachable(source, targetRightDown, Empty.INSTANCE)).isTrue()
        );
    }
}
