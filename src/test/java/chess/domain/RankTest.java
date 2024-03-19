package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 8})
    @DisplayName("1부터 8까지의 세로 위치를 가진다.")
    void createRank(int num) {
        Rank rank = new Rank(num);
        assertThat(rank).isEqualTo(new Rank(num));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    @DisplayName("세로 위치가 1보다 작거나 8보다 크다면 예외가 발생한다.")
    void invalidRank(int num) {
        assertThatThrownBy(() -> new Rank(num))
                .isInstanceOf(IllegalArgumentException.class);
    }
}