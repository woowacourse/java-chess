package domain.piece;

import static org.junit.jupiter.api.Assertions.*;

import domain.piece.info.Color;
import domain.piece.info.File;
import domain.piece.info.Position;
import domain.piece.info.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {
    /*
          T..T..T.  7
          .x.x.x..  6
          ..xxx...  5
          TxxQxxxT  4
          ..xxx...  3
          .x.x.x..  2
          T..T..T.  1 (rank 1)

          abcdefgh
       */
    @Test
    @DisplayName("퀸은 가로, 세로, 대각선 방향으로 움직일 수 있다")
    void isDiagonal() {
        final Piece queen = new Queen(Color.BLACK);
        final Position source = new Position(File.D, Rank.FOUR);
        final Position targetUp = new Position(File.D, Rank.SEVEN);
        final Position targetRightUp = new Position(File.G, Rank.SEVEN);
        final Position targetRight = new Position(File.H, Rank.FOUR);
        final Position targetRightDown = new Position(File.G, Rank.ONE);
        final Position targetDown = new Position(File.D, Rank.ONE);
        final Position targetLeftDown = new Position(File.A, Rank.ONE);
        final Position targetLeft = new Position(File.A, Rank.FOUR);
        final Position targetLeftUp = new Position(File.A, Rank.SEVEN);

        assertAll(
                () -> Assertions.assertThat(queen.isReachable(source, targetUp, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(queen.isReachable(source, targetRightUp, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(queen.isReachable(source, targetRight, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(queen.isReachable(source, targetRightDown, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(queen.isReachable(source, targetDown, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(queen.isReachable(source, targetLeftDown, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(queen.isReachable(source, targetLeft, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(queen.isReachable(source, targetLeftUp, Empty.INSTANCE)).isTrue()
        );
    }

}
