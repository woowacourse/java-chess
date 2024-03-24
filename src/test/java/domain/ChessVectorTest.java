package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ChessVectorTest {

    @DisplayName("벡터를 최소 비율로 스케일 다운 할 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"5,0,1,0", "0,-5,0,-1", "-4,4,-1,1", "-4,-2,-2,-1", "1,1,1,1"})
    void scaleDown(final int x, final int y, final int resultX, final int resultY) {
        // given
        final ChessVector vector = new ChessVector(x, y);

        // when
        final ChessVector direction = vector.scaleDown();

        assertThat(direction).isEqualTo(new ChessVector(resultX, resultY));
    }

    @DisplayName("벡터의 x,y요소 절댓값의 최대 공약수를 구한다.")
    @ParameterizedTest
    @CsvSource(value = {"5,0,5", "0,-3,3", "-4,4,4", "-4,-2,2", "1,2,1"})
    void findAbsGCD(final int x, final int y, final int expected) {
        // given
        final ChessVector vector = new ChessVector(x, y);

        // when
        final int gcd = vector.findAbsGCD();

        // then
        assertThat(gcd).isEqualTo(expected);
    }

    @DisplayName("벡터의 방향이 대각선인지 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"5,5,true", "3,-3,true", "-4,-4,true", "-2,2,true", "-4,2,false", "1,2,false", "0,1,false"})
    void isDiagonal(final int x, final int y, final boolean expected) {
        // given
        final ChessVector vector = new ChessVector(x, y);

        // when
        final boolean diagonal = vector.isDiagonal();

        // then
        assertThat(diagonal).isEqualTo(expected);
    }

    @DisplayName("벡터의 방향이 수평 또는 수직인지 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"5,0,true", "0,-3,true", "-4,0,true", "-2,0,true", "-4,2,false", "1,2,false", "1,1,false"})
    void isHorizontalOrVertical(final int x, final int y, final boolean expected) {
        // given
        final ChessVector vector = new ChessVector(x, y);

        // when
        final boolean horizontalOrVertical = vector.isHorizontalOrVertical();

        // then
        assertThat(horizontalOrVertical).isEqualTo(expected);
    }

    @DisplayName("벡터의 x,y 범위가 모두 -1 ~ 1 이내인지 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"1,0,true", "0,-1,true", "-1,1,true", "1,-1,true", "0,2,false", "1,2,false", "1,3,false"})
    void isManhattanDistance(final int x, final int y, final boolean expected) {
        // given
        final ChessVector vector = new ChessVector(x, y);

        // when
        final boolean manhattanDistance = vector.isManhattanDistance(1);

        // then
        assertThat(manhattanDistance).isEqualTo(expected);
    }
}
