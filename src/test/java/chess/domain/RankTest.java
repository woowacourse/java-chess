package chess.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {

    @Test
    @DisplayName("좌표가 숫자가 아닐 경우 예외가 발생한다.")
    void occurExceptionIfRankNotNumeric() {
        assertThatCode(() -> Rank.from("삼"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Rank.ERROR_NOT_NUMERIC);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "9"})
    @DisplayName("좌표가 범위를 벗어날 경우 예외가 발생한다.")
    void occurExceptionIfRankIsOutOfRange(String rank) {
        assertThatCode(() -> Rank.from(rank))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Rank.ERROR_NOT_EXIST_RANK);
    }
}
