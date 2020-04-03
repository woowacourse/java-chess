package board;

import chess.domain.board.Ypoint;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class YpointTest {

    @ParameterizedTest
    @DisplayName("필드 변수로 갖고 있는 문자를 입력받으면 enum 클래스를 정상 반환해야 함")
    @ValueSource(strings = {"8", "7", "6", "5", "4", "3", "2", "1"})
    void inputVarCharThenReturnYpointClassTest(char input) {
        Assertions.assertThat(Ypoint.of(input)).isInstanceOf(Ypoint.class);
    }

    @ParameterizedTest
    @DisplayName("필드 변수로 갖고 있는 숫자를 입력받으면 enum 클래스를 정상 반환해야 함")
    @ValueSource(strings = {"8", "7", "6", "5", "4", "3", "2", "1"})
    void inputVarNumThenReturnYpointClassTest(int input) {
        Assertions.assertThat(Ypoint.of(input)).isInstanceOf(Ypoint.class);
    }

    @ParameterizedTest
    @DisplayName("필드 변수에 없는 문자를 입력받으면 예외가 발생해야 함")
    @ValueSource(strings = {"9", "0", "a", "ㄱ", "-"})
    void inputNotVarCharThenThrowException(char input) {
        Assertions.assertThatThrownBy(() -> Ypoint.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 세로행입니다.");
    }

    @ParameterizedTest
    @DisplayName("필드 변수에 없는 숫자를 입력받으면 예외가 발생해야 함")
    @ValueSource(strings = {"10", "9", "0", "-1"})
    void inputNotVarNumThenThrowException(int input) {
        Assertions.assertThatThrownBy(() -> Ypoint.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 세로행입니다.");
    }

    @ParameterizedTest
    @DisplayName("Ypoint 간의 차이를 정확히 계산해야 함")
    @CsvSource(value = {"1,1,0", "1,2,-1", "1,3,-2", "1,4,-3", "1,5,-4", "1,6,-5", "1,7,-6", "1,8,-7",
            "8,1,7", "8,2,6", "8,3,5", "8,4,4", "8,5,3", "8,6,2", "8,7,1", "8,8,0"})
    void getGapValueAmongYPoints(int ch1, int ch2, int expected) {
        int result = Ypoint.of(ch1).getGapValue(Ypoint.of(ch2));
        Assertions.assertThat(result).isEqualTo(expected);
    }
}
