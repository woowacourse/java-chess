package domain.piece;

import static org.junit.jupiter.api.Assertions.*;

import domain.piece.info.Color;
import domain.piece.info.File;
import domain.piece.info.Position;
import domain.piece.info.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    /*
      ........  7
      ........  6
      ..TTT...  5
      ..TKT...  4
      ..TTT...  3
      ........  2
      ........  1 (rank 1)

      abcdefgh
   */
    @Test
    @DisplayName("킹은 인접한 8방향으로 움직일 수 있다")
    void isReachable() {
        final Piece king = new King(Color.BLACK);
        final Position source = new Position(File.D, Rank.FOUR);
        final Position targetUp = new Position(File.D, Rank.FIVE);
        final Position targetRightUp = new Position(File.E, Rank.FIVE);
        final Position targetRight = new Position(File.E, Rank.FOUR);
        final Position targetRightDown = new Position(File.E, Rank.THREE);
        final Position targetDown = new Position(File.D, Rank.THREE);
        final Position targetLeftDown = new Position(File.C, Rank.THREE);
        final Position targetLeft = new Position(File.C, Rank.FOUR);
        final Position targetLeftUp = new Position(File.C, Rank.TWO);

        assertAll(
                () -> Assertions.assertThat(king.isReachable(source, targetUp, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(king.isReachable(source, targetRightUp, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(king.isReachable(source, targetRight, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(king.isReachable(source, targetRightDown, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(king.isReachable(source, targetDown, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(king.isReachable(source, targetLeftDown, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(king.isReachable(source, targetLeft, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(king.isReachable(source, targetLeftUp, Empty.INSTANCE)).isTrue()
        );
    }
}
