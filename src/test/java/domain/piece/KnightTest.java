package domain.piece;

import static domain.piece.info.Color.*;
import static domain.piece.info.File.*;
import static domain.piece.info.Rank.*;
import static org.junit.jupiter.api.Assertions.*;

import domain.piece.info.Color;
import domain.piece.info.File;
import domain.piece.info.Position;
import domain.piece.info.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    /*
          ........  7
          ..8.1...  6
          .7...2..  5
          ...N....  4
          .6...3..  3
          ..5.4...  2
          ........  1 (rank 1)

          abcdefgh
       */
    @Test
    @DisplayName("나이트는 (파일 1, 랭크 2) 혹은 (파일 2 , 랭크 1) 만큼 움직일 수 있다")
    void isReachable() {
        final Piece knight = new Knight(BLACK);
        final Position source = new Position(D, FOUR);
        final Position targetOne = new Position(E, SIX);
        final Position targetTwo = new Position(F, FIVE);
        final Position targetThree = new Position(F, THREE);
        final Position targetFour = new Position(E, TWO);
        final Position targetFive = new Position(C, TWO);
        final Position targetSix = new Position(B, THREE);
        final Position targetSeven = new Position(B, FIVE);
        final Position targetEight = new Position(C, SIX);

        assertAll(
                () -> Assertions.assertThat(knight.isReachable(source, targetOne, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(knight.isReachable(source, targetTwo, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(knight.isReachable(source, targetThree, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(knight.isReachable(source, targetFour, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(knight.isReachable(source, targetFive, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(knight.isReachable(source, targetSix, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(knight.isReachable(source, targetSeven, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(knight.isReachable(source, targetEight, Empty.INSTANCE)).isTrue()
        );
    }
}
