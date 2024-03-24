package chess.domain.location;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RowTest {
    @DisplayName("1~8 사이의 숫자를 이용해 객체를 생성할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 8})
    void constructTest(int input) {
        assertThatCode(() -> Row.createByRank(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("범위 이외의 숫자를 이용해 객체를 생성하면 예외가 발생한다.")
    @Test
    void outOfBoundConstructTest() {
        assertThatThrownBy(() -> Row.createByRank(9))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 Row 입력입니다.");
    }

    @DisplayName("숫자가 아닌 값으로 객체를 생성하면 예외가 발생한다.")
    @Test
    void notNumberExceptionTest() {
        assertThatThrownBy(() -> Row.createByRank("A"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 Row 입력입니다. 숫자를 입력해 주세요");
    }

    @DisplayName("Row의 거리를 계산한다.")
    @Test
    void calculateDistanceTest() {

        Row source = Row.SIX;
        Row target = Row.ONE;
        assertThat(source.calculateDistance(target)).isEqualTo(-5);
    }
}
