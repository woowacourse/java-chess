package chess.domain.board.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {

    @Test
    @DisplayName("유효하지 않은 값으로 생성하려 할 때 예외를 발생시킨다.")
    void createException() {
        // given
        String invalidValue = "9";
        // when, then
        assertThatThrownBy(() -> Rank.from(invalidValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 rank 값입니다.");
    }

    @ParameterizedTest
    @DisplayName("Rank 값을 받아 폰의 초기 Rank인지 반환한다.")
    @CsvSource({"TWO, true", "SEVEN, true", "ONE, false"})
    void isPawnRank(final Rank rank, final boolean expected) {
        // when
        final boolean actual = rank.isPawnRank();
        // then
        assertThat(actual).isEqualTo(expected);
    }
}
