package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RankTest {

    @ParameterizedTest(name = "입력: {0}")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
    @DisplayName("주어진 입력값에 따라 Rank를 생성할 수 있는지 확인한다")
    void ofFile_int(final int value) {
        // given
        final int expected = value;

        // when
        Rank rank = Rank.of(value);
        final int actual = rank.value();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("유효하지 않은 숫자로 Rank를 생성하면 예외가 발생한다")
    void throwExceptionWhenInvalidNumber() {
        assertThatThrownBy(() -> Rank.of(9))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당하는 Rank가 존재하지 않습니다.");
    }

    @ParameterizedTest(name = "입력: {0}")
    @CsvSource(value = {"1:1", "2:2", "3:3", "4:4", "5:5", "6:6", "7:7", "8:8"}, delimiter = ':')
    @DisplayName("주어진 입력값에 따라 Rank를 생성할 수 있는지 확인한다")
    void ofFile_int(final char charValue, final int intValue) {
        // given
        final int expected = intValue;

        // when
        Rank rank = Rank.of(charValue);
        final int actual = rank.value();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("유요하지 않은 문자로 Rank를 생성하면 예외가 발생한다")
    void throwExceptionWhenInvalidChar() {
        assertThatThrownBy(() -> Rank.of('i'))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당하는 Rank가 존재하지 않습니다.");
    }
}
