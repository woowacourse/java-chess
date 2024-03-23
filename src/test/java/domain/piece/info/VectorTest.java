package domain.piece.info;

import static domain.PositionFixture.*;
import static domain.piece.info.File.*;
import static domain.piece.info.Rank.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.piece.Piece;
import domain.piece.Queen;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VectorTest {

    @Test
    @DisplayName("두 위치가 같으면 예외가 발생한다")
    void isSamePosition() {
        final Position position = new Position(A, ONE);

        assertThatThrownBy(() -> new Vector(position, position))
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
        final Position source = new Position(D, FOUR);
        final Position targetLeftUp = new Position(A, SEVEN);
        final Position targetRightUp = new Position(G, SEVEN);
        final Position targetLeftDown = new Position(A, ONE);
        final Position targetRightDown = new Position(G, ONE);

        assertAll(
                () -> assertThat(new Vector(source, targetLeftUp).isDiagonal()).isTrue(),
                () -> assertThat(new Vector(source, targetRightUp).isDiagonal()).isTrue(),
                () -> assertThat(new Vector(source, targetLeftDown).isDiagonal()).isTrue(),
                () -> assertThat(new Vector(source, targetRightDown).isDiagonal()).isTrue()
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
        final Position source = new Position(D, FOUR);
        final Position targetUp = new Position(D, SEVEN);
        final Position targetRight = new Position(H, FOUR);
        final Position targetDown = new Position(D, ONE);
        final Position targetLeft = new Position(A, FOUR);

        assertAll(
                () -> assertThat(new Vector(source, targetUp).isStraight()).isTrue(),
                () -> assertThat(new Vector(source, targetRight).isStraight()).isTrue(),
                () -> assertThat(new Vector(source, targetDown).isStraight()).isTrue(),
                () -> assertThat(new Vector(source, targetLeft).isStraight()).isTrue()
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
        final Position source = new Position(D, FOUR);
        final Position targetUp = new Position(D, FIVE);
        final Position targetUpRight = new Position(E, FIVE);
        final Position targetRight = new Position(E, FOUR);
        final Position targetRightDown = new Position(E, THREE);
        final Position targetDown = new Position(D, THREE);
        final Position targetDownLeft = new Position(C, THREE);
        final Position targetLeft = new Position(C, FOUR);
        final Position targetLeftUp = new Position(C, FIVE);

        assertAll(
                () -> assertThat(new Vector(source, targetUp).isUnitVector()).isTrue(),
                () -> assertThat(new Vector(source, targetUpRight).isUnitVector()).isTrue(),
                () -> assertThat(new Vector(source, targetRight).isUnitVector()).isTrue(),
                () -> assertThat(new Vector(source, targetRightDown).isUnitVector()).isTrue(),
                () -> assertThat(new Vector(source, targetDown).isUnitVector()).isTrue(),
                () -> assertThat(new Vector(source, targetDownLeft).isUnitVector()).isTrue(),
                () -> assertThat(new Vector(source, targetLeft).isUnitVector()).isTrue(),
                () -> assertThat(new Vector(source, targetLeftUp).isUnitVector()).isTrue()
        );
    }

    @Test
    @DisplayName("벡터는 파일과 랭크의 절댓값 합을 구할 수 있다")
    void absoluteSum() {
        final Position source = new Position(D, FOUR);
        final Position target = new Position(C, THREE);
        final Vector vector = new Vector(source, target);

        assertThat(vector.absoluteSum()).isEqualTo(2);
    }

    @Test
    @DisplayName("벡터는 주어진 값의 절대값을 갖고 있는지 확인할 수 있다")
    void absoluteValueOf() {
        final Position source = new Position(D, FOUR);
        final Position target = new Position(C, TWO);
        final Vector vector = new Vector(source, target);

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
