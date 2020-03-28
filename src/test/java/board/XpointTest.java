package board;

import chess.domain.board.Xpoint;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class XpointTest {

    @ParameterizedTest
    @DisplayName("필드 변수로 갖고 있는 문자를 입력받으면 enum 클래스를 정상 반환해야 함")
    @ValueSource(strings = {"a", "b", "c", "d", "e", "f", "g", "h"})
    void inputVarCharThenReturnXpointClassTest(char input) {
        Assertions.assertThat(Xpoint.of(input) != null).isTrue();
    }

    @ParameterizedTest
    @DisplayName("필드 변수로 갖고 있는 숫자를 입력받으면 enum 클래스를 정상 반환해야 함")
    @ValueSource(strings = {"1", "2", "3", "4", "5", "6", "7", "8"})
    void inputVarNumThenReturnXpointClassTest(int input) {
        Assertions.assertThat(Xpoint.of(input) != null).isTrue();
    }

    @ParameterizedTest
    @DisplayName("필드 변수에 없는 문자를 입력받으면 예외가 발생해야 함")
    @ValueSource(strings = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "i", "L", "m"})
    void inputNotVarCharThenThrowException(char input) {
        Assertions.assertThatThrownBy(() -> Xpoint.of(input)).hasMessage("존재하지 않는 가로열입니다.");
    }

    @ParameterizedTest
    @DisplayName("필드 변수에 없는 숫자를 입력받으면 예외가 발생해야 함")
    @ValueSource(strings = {"-2", "-1", "0", "9", "10", "11", "12"})
    void inputNotVarNumThenThrowException(int input) {
        Assertions.assertThatThrownBy(() -> Xpoint.of(input)).hasMessage("존재하지 않는 가로열입니다.");
    }

    @ParameterizedTest
    @DisplayName("Xpoint 간의 차이를 정확히 계산해야 함")
    @CsvSource(value = {"a,a,0", "a,b,-1", "a,c,-2", "a,d,-3", "a,e,-4", "a,f,-5", "a,g,-6", "a,h,-7",
            "h,a,7", "h,b,6", "h,c,5", "h,d,4", "h,e,3", "h,f,2", "h,g,1", "h,h,0"})
    void getGapValueAmongXPoints(char ch1, char ch2, int expected) {
        int result = Xpoint.of(ch1).getGapValue(Xpoint.of(ch2));
        Assertions.assertThat(result).isEqualTo(expected);
    }
}
