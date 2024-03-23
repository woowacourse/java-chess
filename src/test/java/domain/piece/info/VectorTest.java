package domain.piece.info;

import static org.junit.jupiter.api.Assertions.assertAll;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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

    @Test
    @DisplayName("벡터는 파일과 랭크의 절댓값 합을 구할 수 있다")
    void absoluteSum() {
        final Position source = new Position(File.D, Rank.FOUR);
        final Position target = new Position(File.C, Rank.THREE);
        final Vector vector = new Vector(source, target);

        Assertions.assertThat(vector.absoluteSum()).isEqualTo(2);
    }

    @Test
    @DisplayName("벡터는 주어진 값의 절대값을 갖고 있는지 확인할 수 있다")
    void absoluteValueOf() {
        final Position source = new Position(File.D, Rank.FOUR);
        final Position target = new Position(File.C, Rank.TWO);
        final Vector vector = new Vector(source, target);

        assertAll(
                () -> Assertions.assertThat(vector.hasAbsoluteValueOf(1)).isTrue(),
                () -> Assertions.assertThat(vector.hasAbsoluteValueOf(2)).isTrue(),
                () -> Assertions.assertThat(vector.hasAbsoluteValueOf(3)).isFalse()
        );
    }

    @Test
    @DisplayName("x축 대칭이동을 시킬 수 있다")
    void reflect() {
        final Vector vector = Vector.of(0, 1);
        final Vector expected = Vector.of(0, -1);

        final Vector reflected = vector.reflectHorizontally();

        Assertions.assertThat(reflected).isEqualTo(expected);
    }
}
