package board;

import chess.domain.board.Positions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PositionTest {

    @ParameterizedTest
    @DisplayName("목표 위치값의 x좌표를 가리키는 방향값을 정상적으로 계산해야 함")
    @CsvSource(value = {"a1,a3,0", "b2,e5,1", "f3,d8,-1"})
    void getXPointDirectionValueToTest(String source, String target, int expected) {
        int result = Positions.of(source).getXPointDirectionValueTo(Positions.of(target));
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("목표 위치값의 y좌표를 가리키는 방향값을 정상적으로 계산해야 함")
    @CsvSource(value = {"h1,h1,0", "d3,d7,1", "f6,f2,-1"})
    void getYPointDirectionValueToTest(String source, String target, int expected) {
        int result = Positions.of(source).getYPointDirectionValueTo(Positions.of(target));
        Assertions.assertThat(result).isEqualTo(expected);
    }
}
