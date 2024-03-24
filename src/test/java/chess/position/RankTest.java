package chess.position;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    @DisplayName("Rank와 Rank간 거리를 계산할 수 있다.")
    void should_calculate_distance() {
        Rank rankOne = Rank.ONE;
        Rank rankTwo = Rank.TWO;

        assertThat(rankOne.calculateDifferenceTo(rankTwo)).isEqualTo(1);
    }
}
