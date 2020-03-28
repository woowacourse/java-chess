package chess.coordinate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VectorTest {

    @DisplayName("1 ~ 8 까지의 값을 입력한 경우 정상적으로 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"1,ONE", "2,TWO", "3,THREE", "4,FOUR", "5,FIVE", "6,SIX", "7,SEVEN", "8,EIGHT"})
    void findByValue(int value, Rank expect) {
        Rank actual = Rank.findByValue(value);

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("1 ~ 8 을 벗어난 값을 입력한 경우 Exception")
    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    void findByValue1(int wrongValue) {
        assertThatThrownBy(() -> Rank.findByValue(wrongValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("%d : 1 ~ 8 을 벗어났습니다.", wrongValue);
    }

    @DisplayName("절대 값의 합을 구하는 테스트.")
    @ParameterizedTest
    @CsvSource(value = {"1,2,3", "-1,2,3", "0,0,0"})
    void sumOfAbsolute(int fileVariation, int rankVariation, int expect) {
        Vector vector = new Vector(fileVariation, rankVariation);
        assertThat(vector.sumOfAbsolute()).isEqualTo(expect);
    }

    @DisplayName("절대 값의 차를 구하는 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1,2,1", "-1,2,1", "0,0,0"})
    void subtractOfAbsolute(int fileVariation, int rankVariation, int expect) {
        Vector vector = new Vector(fileVariation, rankVariation);
        assertThat(vector.subtractOfAbsolute()).isEqualTo(expect);
    }

    @DisplayName("대각선인지 확인")
    @ParameterizedTest
    @CsvSource(value = {"1,2,false", "-1,1,true"})
    void isDiagonal(int fileVariation, int rankVariation, boolean expect) {
        Vector vector = new Vector(fileVariation, rankVariation);
        assertThat(vector.isDiagonal()).isEqualTo(expect);
    }

    @DisplayName("절대 값이 range 안에 있는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"0,1,1,true", "-1,1,2,true", "-3,2,1,false"})
    void isRangeUnderAbsolute(int fileVariation, int rankVariation, int value, boolean expect) {
        Vector vector = new Vector(fileVariation, rankVariation);
        assertThat(vector.isRangeUnderAbsolute(value)).isEqualTo(expect);
    }

    @DisplayName("직선인지 확인")
    @ParameterizedTest
    @CsvSource(value = {"0,1,true", "5,0,true", "-3,1,false"})
    void isStraight(int fileVariation, int rankVariation, boolean expect) {
        Vector vector = new Vector(fileVariation, rankVariation);
        assertThat(vector.isStraight()).isEqualTo(expect);
    }

    @DisplayName("unit vector 나오는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"3,3,RIGHT_UP", "5,0,RIGHT", "-3,3,LEFT_UP"})
    void getUnitVector(int fileVariation, int rankVariation, Direction direction) {
        Vector vector = new Vector(fileVariation, rankVariation);
        assertThat(vector.getUnitVector()).isEqualTo(direction);
    }

}