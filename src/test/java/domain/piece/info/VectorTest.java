package domain.piece.info;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class VectorTest {

    @Test
    @DisplayName("두 위치가 같으면 예외가 발생한다")
    void isSamePosition() {
        final Position position = new Position(File.A, Rank.ONE);
        Assertions.assertThatThrownBy(() -> new Vector(position, position))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("두 위치가 같습니다");
    }


    /*
        T.....T.  7
        .x...x..  6
        ..x.x...  5
        ...S....  4
        ..x.x...  3
        .x...x..  2
        T.....T.  1 (rank 1)

        abcdefgh
     */
    @Test
    @DisplayName("주어진 두 위치가 대각선상에 위치하는지 확인한다")
    void isDiagonal() {
        final Position source = new Position(File.D, Rank.FOUR);
        final Position targetLeftUp = new Position(File.A, Rank.SEVEN);
        final Position targetRightUp = new Position(File.G, Rank.SEVEN);
        final Position targetLeftDown = new Position(File.A, Rank.ONE);
        final Position targetRightDown = new Position(File.G, Rank.ONE);

        assertAll(
                () -> Assertions.assertThat(new Vector(source, targetLeftUp).isDiagonal()).isTrue(),
                () -> Assertions.assertThat(new Vector(source, targetRightUp).isDiagonal()).isTrue(),
                () -> Assertions.assertThat(new Vector(source, targetLeftDown).isDiagonal()).isTrue(),
                () -> Assertions.assertThat(new Vector(source, targetRightDown).isDiagonal()).isTrue()
        );
    }

    /*
     ...T....  7
     ...x....  6
     ...x....  5
     TxxSxxxT  4
     ...x....  3
     ...x....  2
     ...T....  1 (rank 1)

     abcdefgh
  */
    @Test
    @DisplayName("주어진 두 위치가 일직선상에 위치하는지 확인한다")
    void isStaright() {
        final Position source = new Position(File.D, Rank.FOUR);
        final Position targetUp = new Position(File.D, Rank.SEVEN);
        final Position targetRight = new Position(File.H, Rank.FOUR);
        final Position targetDown = new Position(File.D, Rank.ONE);
        final Position targetLeft = new Position(File.A, Rank.FOUR);

        assertAll(
                () -> Assertions.assertThat(new Vector(source, targetUp).isStraight()).isTrue(),
                () -> Assertions.assertThat(new Vector(source, targetRight).isStraight()).isTrue(),
                () -> Assertions.assertThat(new Vector(source, targetDown).isStraight()).isTrue(),
                () -> Assertions.assertThat(new Vector(source, targetLeft).isStraight()).isTrue()
        );
    }

    /*
     ........  7
     ........  6
     ..TTT...  5
     ..TST...  4
     ..TTT...  3
     ........  2
     ........  1 (rank 1)

     abcdefgh
  */
    @Test
    @DisplayName("주어진 두 위치가 인접해 있는지 확인한다")
    void isNearest() {
        final Position source = new Position(File.D, Rank.FOUR);
        final Position targetUp = new Position(File.D, Rank.FIVE);
        final Position targetUpRight = new Position(File.E, Rank.FIVE);
        final Position targetRight = new Position(File.E, Rank.FOUR);
        final Position targetRightDown = new Position(File.E, Rank.THREE);
        final Position targetDown = new Position(File.D, Rank.THREE);
        final Position targetDownLeft = new Position(File.C, Rank.THREE);
        final Position targetLeft = new Position(File.C, Rank.FOUR);
        final Position targetLeftUp = new Position(File.C, Rank.FIVE);

        assertAll(
                () -> Assertions.assertThat(new Vector(source, targetUp).isUnitVector()).isTrue(),
                () -> Assertions.assertThat(new Vector(source, targetUpRight).isUnitVector()).isTrue(),
                () -> Assertions.assertThat(new Vector(source, targetRight).isUnitVector()).isTrue(),
                () -> Assertions.assertThat(new Vector(source, targetRightDown).isUnitVector()).isTrue(),
                () -> Assertions.assertThat(new Vector(source, targetDown).isUnitVector()).isTrue(),
                () -> Assertions.assertThat(new Vector(source, targetDownLeft).isUnitVector()).isTrue(),
                () -> Assertions.assertThat(new Vector(source, targetLeft).isUnitVector()).isTrue(),
                () -> Assertions.assertThat(new Vector(source, targetLeftUp).isUnitVector()).isTrue()
        );
    }
}
