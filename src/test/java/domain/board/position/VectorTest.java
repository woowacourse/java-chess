package domain.board.position;

import static domain.PositionFixture.A_FOUR;
import static domain.PositionFixture.A_ONE;
import static domain.PositionFixture.A_SEVEN;
import static domain.PositionFixture.B_FOUR;
import static domain.PositionFixture.B_SIX;
import static domain.PositionFixture.B_TWO;
import static domain.PositionFixture.C_FIVE;
import static domain.PositionFixture.C_FOUR;
import static domain.PositionFixture.C_THREE;
import static domain.PositionFixture.C_TWO;
import static domain.PositionFixture.D_FIVE;
import static domain.PositionFixture.D_FOUR;
import static domain.PositionFixture.D_ONE;
import static domain.PositionFixture.D_SEVEN;
import static domain.PositionFixture.D_SIX;
import static domain.PositionFixture.D_THREE;
import static domain.PositionFixture.D_TWO;
import static domain.PositionFixture.E_FIVE;
import static domain.PositionFixture.E_FOUR;
import static domain.PositionFixture.E_THREE;
import static domain.PositionFixture.F_SIX;
import static domain.PositionFixture.F_TWO;
import static domain.PositionFixture.G_FOUR;
import static domain.PositionFixture.G_ONE;
import static domain.PositionFixture.G_SEVEN;
import static domain.PositionFixture.H_FOUR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.board.position.Position;
import domain.board.position.Vector;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VectorTest {

    @Test
    @DisplayName("두 위치가 같으면 예외가 발생한다")
    void isSamePosition() {
        assertThatThrownBy(() -> new Vector(A_ONE, A_ONE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("두 위치는 같을 수 없습니다.");
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
        assertAll(
                () -> assertThat(new Vector(D_FOUR, A_SEVEN).isDiagonal()).isTrue(),
                () -> assertThat(new Vector(D_FOUR, G_SEVEN).isDiagonal()).isTrue(),
                () -> assertThat(new Vector(D_FOUR, A_ONE).isDiagonal()).isTrue(),
                () -> assertThat(new Vector(D_FOUR, G_ONE).isDiagonal()).isTrue()
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
    void isStraight() {
        assertAll(
                () -> assertThat(new Vector(D_FOUR, D_SEVEN).isStraight()).isTrue(),
                () -> assertThat(new Vector(D_FOUR, H_FOUR).isStraight()).isTrue(),
                () -> assertThat(new Vector(D_FOUR, D_ONE).isStraight()).isTrue(),
                () -> assertThat(new Vector(D_FOUR, A_FOUR).isStraight()).isTrue()
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
        assertAll(
                () -> assertThat(new Vector(D_FOUR, D_FIVE).allAbsoluteValueSmallerOrEqualThanOne()).isTrue(),
                () -> assertThat(new Vector(D_FOUR, E_FIVE).allAbsoluteValueSmallerOrEqualThanOne()).isTrue(),
                () -> assertThat(new Vector(D_FOUR, E_FOUR).allAbsoluteValueSmallerOrEqualThanOne()).isTrue(),
                () -> assertThat(new Vector(D_FOUR, E_THREE).allAbsoluteValueSmallerOrEqualThanOne()).isTrue(),
                () -> assertThat(new Vector(D_FOUR, D_THREE).allAbsoluteValueSmallerOrEqualThanOne()).isTrue(),
                () -> assertThat(new Vector(D_FOUR, C_THREE).allAbsoluteValueSmallerOrEqualThanOne()).isTrue(),
                () -> assertThat(new Vector(D_FOUR, C_FOUR).allAbsoluteValueSmallerOrEqualThanOne()).isTrue(),
                () -> assertThat(new Vector(D_FOUR, C_FIVE).allAbsoluteValueSmallerOrEqualThanOne()).isTrue()
        );
    }

    @Test
    @DisplayName("벡터는 파일과 랭크의 절댓값 합을 구할 수 있다")
    void absoluteSum() {
        final Vector vector = new Vector(D_FOUR, C_THREE);

        assertThat(vector.absoluteSum()).isEqualTo(2);
    }

    @Test
    @DisplayName("벡터는 주어진 값의 절대값을 갖고 있는지 확인할 수 있다")
    void absoluteValueOf() {
        final Vector vector = new Vector(D_FOUR, C_TWO);

        assertAll(
                () -> assertThat(vector.hasAbsoluteValueOf(1)).isTrue(),
                () -> assertThat(vector.hasAbsoluteValueOf(2)).isTrue(),
                () -> assertThat(vector.hasAbsoluteValueOf(3)).isFalse()
        );
    }

    @Test
    @DisplayName("x축 대칭이동을 시킬 수 있다")
    void reflect() {
        final Vector vector = Vector.of(0, 1);
        final Vector expected = Vector.of(0, -1);

        final Vector reflected = vector.reflectHorizontally();

        assertThat(reflected).isEqualTo(expected);
    }


    /*
             8..1..2.  7
             .x.x.x..  6
             ..xxx...  5
             7xxSxxx3  4
             ..xxx...  3
             .x.x.x..  2
             6..5..4.  1 (rank 1)

             abcdefgh
          */
    @Test
    @DisplayName("시작과 끝을 제외한 경로의 위치들을 생성할 수 있다")
    void path() {
        final Position source = D_FOUR;
        final List<Position> positionsOne = new Vector(source, D_SEVEN).generatePathExcludingEndpoints(source);
        final List<Position> positionsTwo = new Vector(source, G_SEVEN).generatePathExcludingEndpoints(source);
        final List<Position> positionsThree = new Vector(source, H_FOUR).generatePathExcludingEndpoints(source);
        final List<Position> positionsFour = new Vector(source, G_ONE).generatePathExcludingEndpoints(source);
        final List<Position> positionsFive = new Vector(source, D_ONE).generatePathExcludingEndpoints(source);
        final List<Position> positionsSix = new Vector(source, A_ONE).generatePathExcludingEndpoints(source);
        final List<Position> positionsSeven = new Vector(source, A_FOUR).generatePathExcludingEndpoints(source);
        final List<Position> positionsEight = new Vector(source, A_SEVEN).generatePathExcludingEndpoints(source);

        assertAll(
                () -> assertThat(positionsOne).contains(D_FIVE, D_SIX).hasSize(2),
                () -> assertThat(positionsTwo).contains(E_FIVE, F_SIX).hasSize(2),
                () -> assertThat(positionsThree).contains(E_FOUR, G_FOUR).hasSize(3),
                () -> assertThat(positionsFour).contains(E_THREE, F_TWO).hasSize(2),
                () -> assertThat(positionsFive).contains(D_THREE, D_TWO).hasSize(2),
                () -> assertThat(positionsSix).contains(C_THREE, B_TWO).hasSize(2),
                () -> assertThat(positionsSeven).contains(C_FOUR, B_FOUR).hasSize(2),
                () -> assertThat(positionsEight).contains(C_FIVE, B_SIX).hasSize(2)
        );
    }
}
