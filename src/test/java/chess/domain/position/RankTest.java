package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RankTest {

    @ParameterizedTest
    @ValueSource(strings = {"-1", "9", "a", "b", "."})
    @DisplayName("유효하지 않은 위치 입력시 예외 발생")
    void wrongPositionInput(String y) {
        assertThatThrownBy(() -> Rank.findRow(y))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("올바르지 않은 rank 위치 정보입니다.");
    }

    @ParameterizedTest
    @DisplayName("유효한 위치 입력시")
    @CsvSource(value = {"8:0", "1:7"}, delimiter = ':')
    void rightPositionInput(String input, int expected) {
        assertThat(Rank.findRow(input)).isEqualTo(expected);
    }
}
