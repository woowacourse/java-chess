package piece.direction;

import chess.domain.piece.direction.Direction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DirectionTest {

    @ParameterizedTest
    @DisplayName("정상적인 x방향, y방향값이 들어오면 Direction 클래스를 정상적으로 반환해야 함")
    @CsvSource(value = {"0,1", "1,1", "1,0", "1,-1", "0,-1", "-1,-1", "-1,0", "-1,1",
            "1,2", "-1,2", "1,-2", "-1,-2", "2,1", "2,-1", "-2,1", "-2,-1"})
    void inputRightDirectionValueThenReturnDirectionClass(int xPointDirectionValue, int yPointDirectionValue) {
        Assertions.assertThat(Direction.of(xPointDirectionValue, yPointDirectionValue) != null).isTrue();
    }

    @ParameterizedTest
    @DisplayName("존재하지 않는 x방향값, y방향값이 들어오면 예외가 발생해야 함")
    @CsvSource(value = {"3,1", "0,-2", "2,3", "5,3"})
    void inputWrongDirectionValueThenThrowException(int xPointDirectionValue, int yPointDirectionValue) {
        Assertions.assertThatThrownBy(() -> Direction.of(xPointDirectionValue, yPointDirectionValue)).hasMessage(
                "존재하지 않는 방향입니다.");
    }
}
