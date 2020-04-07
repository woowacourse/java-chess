package chess.domain.coordinate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RankTest {

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


}