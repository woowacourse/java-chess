package domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import domain.piece.info.Color;
import domain.piece.info.File;
import domain.piece.info.Position;
import domain.piece.info.Rank;
import domain.piece.info.Vector;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {


    /*
          ...T....  7
          ...x....  6
          ...x....  5
          TxxRxxxT  4
          ...x....  3
          ...x....  2
          ...T....  1 (rank 1)

          abcdefgh
    */
    @Test

    @DisplayName("룩은 가로, 세로 방향으로 이동할 수 있다")
    void movement() {
        final Piece rook = new Rook(Color.BLACK);
        final Position source = new Position(File.D, Rank.FOUR);
        final Position targetUp = new Position(File.D, Rank.SEVEN);
        final Position targetRight = new Position(File.H, Rank.FOUR);
        final Position targetDown = new Position(File.D, Rank.ONE);
        final Position targetLeft = new Position(File.A, Rank.FOUR);

        assertAll(
                () -> Assertions.assertThat(rook.isReachable(source, targetUp, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(rook.isReachable(source, targetRight, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(rook.isReachable(source, targetDown, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(rook.isReachable(source, targetLeft, Empty.INSTANCE)).isTrue()
        );
    }

}
