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

class QueenTest {
    /*
          8..1..2.  7
          .x.x.x..  6
          ..xxx...  5
          7xxQxxx3  4
          ..xxx...  3
          .x.x.x..  2
          6..5..4.  1 (rank 1)

          abcdefgh
       */
    @Test
    @DisplayName("퀸은 가로, 세로, 대각선 방향으로 움직일 수 있다")
    void isDiagonal() {
        final Piece queen = new Queen(Color.BLACK);
        final Position source = new Position(File.D, Rank.FOUR);
        final Position targetOne = new Position(File.D, Rank.SEVEN);
        final Position targetTwo = new Position(File.G, Rank.SEVEN);
        final Position targetThree = new Position(File.H, Rank.FOUR);
        final Position targetFour = new Position(File.G, Rank.ONE);
        final Position targetFive = new Position(File.D, Rank.ONE);
        final Position targetSix = new Position(File.A, Rank.ONE);
        final Position targetSeven = new Position(File.A, Rank.FOUR);
        final Position targetEight = new Position(File.A, Rank.SEVEN);

        assertAll(
                () -> assertThat(queen.isReachable(new Vector(source, targetOne), Empty.INSTANCE)).isTrue(),
                () -> assertThat(queen.isReachable(new Vector(source, targetTwo), Empty.INSTANCE)).isTrue(),
                () -> assertThat(queen.isReachable(new Vector(source, targetThree), Empty.INSTANCE)).isTrue(),
                () -> assertThat(queen.isReachable(new Vector(source, targetFour), Empty.INSTANCE)).isTrue(),
                () -> assertThat(queen.isReachable(new Vector(source, targetFive), Empty.INSTANCE)).isTrue(),
                () -> assertThat(queen.isReachable(new Vector(source, targetSix), Empty.INSTANCE)).isTrue(),
                () -> assertThat(queen.isReachable(new Vector(source, targetSeven), Empty.INSTANCE)).isTrue(),
                () -> assertThat(queen.isReachable(new Vector(source, targetEight), Empty.INSTANCE)).isTrue()
        );
    }

}
