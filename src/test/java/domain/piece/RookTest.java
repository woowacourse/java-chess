package domain.piece;

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
        final Position source = new Position(D, FOUR);
        final Position targetOne = new Position(D, SEVEN);
        final Position targetTwo = new Position(H, FOUR);
        final Position targetThree = new Position(D, ONE);
        final Position targetFour = new Position(A, FOUR);

        assertAll(
                () -> assertThat(rook.isReachable(new Vector(source, targetOne), Empty.INSTANCE)).isTrue(),
                () -> assertThat(rook.isReachable(new Vector(source, targetTwo), Empty.INSTANCE)).isTrue(),
                () -> assertThat(rook.isReachable(new Vector(source, targetThree), Empty.INSTANCE)).isTrue(),
                () -> assertThat(rook.isReachable(new Vector(source, targetFour), Empty.INSTANCE)).isTrue()
        );
    }
}
