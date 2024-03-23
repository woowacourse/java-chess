package domain.piece;

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

class KingTest {

    /*
      ........  7
      ........  6
      ..812...  5
      ..7K3...  4
      ..654...  3
      ........  2
      ........  1 (rank 1)

      abcdefgh
   */
    @Test
    @DisplayName("킹은 인접한 8방향으로 움직일 수 있다")
    void isReachable() {
        final Piece king = new King(Color.BLACK);
        final Position source = new Position(File.D, Rank.FOUR);
        final Position targetOne = new Position(File.D, Rank.FIVE);
        final Position targetTwo = new Position(File.E, Rank.FIVE);
        final Position targetThree = new Position(File.E, Rank.FOUR);
        final Position targetFour = new Position(File.E, Rank.THREE);
        final Position targetFive = new Position(File.D, Rank.THREE);
        final Position targetSix = new Position(File.C, Rank.THREE);
        final Position targetSeven = new Position(File.C, Rank.FOUR);
        final Position targetEight = new Position(File.C, Rank.TWO);

        assertAll(
                () -> assertThat(king.isReachable(new Vector(source, targetOne), Empty.INSTANCE)).isTrue(),
                () -> assertThat(king.isReachable(new Vector(source, targetTwo), Empty.INSTANCE)).isTrue(),
                () -> assertThat(king.isReachable(new Vector(source, targetThree), Empty.INSTANCE)).isTrue(),
                () -> assertThat(king.isReachable(new Vector(source, targetFour), Empty.INSTANCE)).isTrue(),
                () -> assertThat(king.isReachable(new Vector(source, targetFive), Empty.INSTANCE)).isTrue(),
                () -> assertThat(king.isReachable(new Vector(source, targetSix), Empty.INSTANCE)).isTrue(),
                () -> assertThat(king.isReachable(new Vector(source, targetSeven), Empty.INSTANCE)).isTrue(),
                () -> assertThat(king.isReachable(new Vector(source, targetEight), Empty.INSTANCE)).isTrue()
        );
    }
}
