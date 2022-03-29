package chess2.util2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionConverterUtilTest {

    @DisplayName("toFileIdx 메서드는 문자열의 첫번째 값이 a~h인 경우 0~7로 변환하여 반환한다.")
    @ParameterizedTest(name = "rank {0}: index {1}")
    @CsvSource(value = {"a1,0", "b2,1", "c1,2", "d5,3", "e2,4", "f4,5", "g2,6", "h4,7"})
    void toFileIdx_ok(String positionKey, int expected) {
        int actual = PositionConverterUtil.toFileIdx(positionKey);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("toFileIdx 메서드는 문자열의 첫번째 값이 a~h이 아닌 경우 예외를 발생시킨다.")
    @Test
    void toFileIdx_exceptionOnInvalidRange() {
        assertThatCode(() -> PositionConverterUtil.toFileIdx("z1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 포지션입니다. (a1~h8)");
    }

    @DisplayName("toRankIdx 메서드는 문자열의 두번째 값이 1~8인 경우 0~7로 변환하여 반환한다.")
    @ParameterizedTest(name = "file {0}: index {1}")
    @CsvSource(value = {"a1,0", "b2,1", "a3,2", "f4,3", "h5,4", "a6,5", "c7,6", "e8,7"})
    void toRankIdx_ok(String positionKey, int expected) {
        int actual = PositionConverterUtil.toRankIdx(positionKey);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("toRankIdx 메서드는 문자열의 두번째 값이 1~8이 아닌 경우 예외를 발생시킨다.")
    @Test
    void toRankIdx_exceptionOnInvalidRange() {
        assertThatCode(() -> PositionConverterUtil.toRankIdx("a0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 포지션입니다. (a1~h8)");
    }

}