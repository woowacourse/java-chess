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
        final Position source = new Position(File.D, Rank.FOUR);
        final Position targetOne = new Position(File.A, Rank.SEVEN);
        final Position targetTwo = new Position(File.G, Rank.SEVEN);
        final Position targetThree = new Position(File.A, Rank.ONE);
        final Position targetFour = new Position(File.G, Rank.ONE);

        assertAll(
                () -> Assertions.assertThat(bishop.isReachable(source, targetOne, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(bishop.isReachable(source, targetTwo, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(bishop.isReachable(source, targetThree, Empty.INSTANCE)).isTrue(),
                () -> Assertions.assertThat(bishop.isReachable(source, targetFour, Empty.INSTANCE)).isTrue()
        );
    }
}
