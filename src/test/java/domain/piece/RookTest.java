package domain.piece;

import static domain.PositionFixture.A_FOUR;
import static domain.PositionFixture.D_FOUR;
import static domain.PositionFixture.D_ONE;
import static domain.PositionFixture.D_SEVEN;
import static domain.PositionFixture.H_FOUR;
import static domain.piece.info.Color.*;
import static domain.piece.info.File.*;
import static domain.piece.info.Rank.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import domain.piece.info.Position;
import domain.piece.info.Vector;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {


    /*
          ...1....  7
          ...x....  6
          ...x....  5
          4xxRxxx2  4
          ...x....  3
          ...x....  2
          ...3....  1 (rank 1)

          abcdefgh
    */
    @Test
    @DisplayName("룩은 가로, 세로 방향으로 이동할 수 있다")
    void movement() {
        final Piece rook = new Rook(BLACK);

        assertAll(
                () -> assertThat(rook.isReachable(new Vector(D_FOUR, D_SEVEN), Empty.INSTANCE)).isTrue(),
                () -> assertThat(rook.isReachable(new Vector(D_FOUR, H_FOUR), Empty.INSTANCE)).isTrue(),
                () -> assertThat(rook.isReachable(new Vector(D_FOUR, D_ONE), Empty.INSTANCE)).isTrue(),
                () -> assertThat(rook.isReachable(new Vector(D_FOUR, A_FOUR), Empty.INSTANCE)).isTrue()
        );
    }
}
