package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @Test
    @DisplayName("Rank와 Rank간 거리를 계산할 수 있다.")
    void should_calculate_distance() {
        Rank rankOne = Rank.ONE;
        Rank rankTwo = Rank.TWO;

        RankDifference expectedDistance = rankOne.calculateDifference(rankTwo);

        assertThat(expectedDistance).isEqualTo(new RankDifference(1));
    }
}
