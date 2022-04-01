package chess.model.position;

import static chess.model.position.Rank.EIGHT;
import static chess.model.position.Rank.ONE;
import static chess.model.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {

    @DisplayName("1~8까지의 숫자가 아닌 숫자가 들어오면 예외를 발생한다.")
    @ParameterizedTest()
    @ValueSource(ints = {-1, 9, 0, 11})
    void of_exception() {
        assertThatThrownBy(() -> Rank.of(9))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 잘못된 값이 입력 되었습니다.");
    }

    @DisplayName("Rank 이동할수 있으면 true를 반환한다.")
    @Test()
    void canAdd_true() {
        assertThat(EIGHT.canAdd(1)).isTrue();
    }

    @DisplayName("Rank 이동할수 없으면 false를 반환한다.")
    @Test
    void canAdd_false() {
        assertThat(ONE.canAdd(1)).isFalse();
    }

    @DisplayName("Rank를 이동한다.")
    @Test
    void add() {
        assertThat(ONE.add(-1)).isEqualTo(TWO);
    }
}
